package service;

import java.util.List;

import model.T_monitorinfo;
import model.sys.T_factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bowl.FactorySettingBowl;
import bowl.PointBowl;
import dao.T_monitorinfoMapper;
import dao.sys.T_factoryMapper;
import factory.MapperBeanFactory;

public class ApplicationRegisterUtil {
	private static ApplicationContext ac = new FileSystemXmlApplicationContext(
			"classpath:applicationContext.xml");

	public static void redistAllfactory() {
		T_factoryMapper mapper = getBean("t_factoryMapper");
		List<T_factory> factories = mapper.selectByExample(null);
		for (T_factory factory : factories) {
			FactorySettingBowl.getInstance().Register(factory);
		}
		MapperBeanFactory.register();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String classname) {
		return (T) ac.getBean(classname);
	}

	public static <T> void redistAllPoint() {
		List<T_factory> factories = FactorySettingBowl.getInstance()
				.getAllFactory();
		for (T_factory factory : factories) {
			T_monitorinfoMapper mapper = MapperBeanFactory.getMapper(
					factory.getFactory_id(), T_monitorinfoMapper.class);
			if (mapper == null) {
				System.out.println("：：未注册：：" + factory.getFac_name());
				continue;
			}
			List<T_monitorinfo> monitorinfos = mapper.selectByExample(null);
			for (T_monitorinfo monitorinfo : monitorinfos) {
				// System.out.println("factory:" + factory.getFac_name()
				// + ",point:" + monitorinfo.getProgram_id());
				PointBowl.getInstance().registerPoint(monitorinfo, factory);
			}
		}
	}

}
