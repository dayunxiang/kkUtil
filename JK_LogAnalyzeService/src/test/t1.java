package test;

//import javax.sql.DataSource;

import model.sys.T_factory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;

import dao.sys.T_factoryMapper;

public class t1 {
	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) throws Exception {
		String driverClassName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://114.215.157.181:3306/db_system?characterEncoding=UTF-8";
		String username="root";
		String password="jkyf9713at";
		//alibaba's DruidDataSource
		DruidDataSource dataSource = new DruidDataSource(); 
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		SqlSessionFactory factory=bean.getObject();
		factory.getConfiguration().addMapper(T_factoryMapper.class);
		
		
		MapperFactoryBean<T> bean2=new MapperFactoryBean<>();
		bean2.setSqlSessionFactory(factory);
		bean2.setMapperInterface((Class<T>) T_factoryMapper.class);
		
		T_factoryMapper mapper=(T_factoryMapper) bean2.getObject();
		
//		T_factoryExample example=new T_factoryExample();
//		example.createCriteria().a
		T_factory factories=mapper.selectByPrimaryKey(1071040538);
		
		System.out.println(factories.getFac_address());
		
//		DataSource dataSource = new PooledDataSource(driverClassName,url,username,password);  
//		Environment environment = new Environment(xmlPath, new JdbcTransactionFactory(), dataSource);  
//		Configuration configuration = new Configuration(environment);  
//		configuration.addMapper(obj);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);  
//		SqlSession session = sqlSessionFactory.openSession();
		}
}
