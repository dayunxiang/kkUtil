package factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;

import bowl.FactorySettingBowl;

import com.alibaba.druid.pool.DruidDataSource;

import dao.T_monitorinfoMapper;
import model.sys.T_factory;

public class MapperBeanFactory {

	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static Map<Integer, MapperFactoryBean<?>> mapperFactorys=new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> void register() {
		List<T_factory> factories = FactorySettingBowl.getInstance()
				.getAllFactory();
		for (T_factory factory : factories) {
			if (!factory.getFac_ip().equals("114.215.157.181")||factory.getFactory_id()==32341)
				continue;

			DruidDataSource dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl("jdbc:mysql://" + factory.getFac_ip() + ":"
					+ factory.getFac_port() + "/"
					+ factory.getFactory_database()
					+ "?characterEncoding=UTF-8");
			dataSource.setUsername(factory.getFac_usr());
			dataSource.setPassword(factory.getFac_pwd());

			dataSource.setRemoveAbandoned(true);
			dataSource.setRemoveAbandonedTimeout(150);

			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			SqlSessionFactory sessionFactory = null;
			try {
				sessionFactory = bean.getObject();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			sessionFactory.getConfiguration().addMapper(
					T_monitorinfoMapper.class);

			MapperFactoryBean<T> bean2 = new MapperFactoryBean<>();
			bean2.setSqlSessionFactory(sessionFactory);
			bean2.setMapperInterface((Class<T>) T_monitorinfoMapper.class);

			mapperFactorys.put(factory.getFactory_id(), bean2);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getMapper(Integer factoryid, Class<T> clss) {
		try {
			Object object=mapperFactorys.get(factoryid).getObject();
			if(object==null)
				return null;
			return (T) object;
		} catch (Exception e) {
			return null;
		}

	}
}
