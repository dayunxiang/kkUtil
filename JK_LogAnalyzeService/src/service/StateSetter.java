package service;

import java.util.List;

import model.sys.T_factory;
import bowl.FactorySettingBowl;
import dao.sys.T_factoryMapper;

public class StateSetter implements Runnable {

	@Override
	public void run() {
		set();
	}

	private void set() {
		T_factoryMapper mapper = ApplicationRegisterUtil.getBean("t_factoryMapper");
		List<T_factory> factories = mapper.selectByExample(null);
		for (T_factory factory : factories) {
			if (FactorySettingBowl.getInstance().getFactoryState(
					factory.getFactory_id())) {
//				System.out.println("连接中：：："+factory.getFac_name());
				T_factory insertFac=new T_factory();
				insertFac.setFactory_id(factory.getFactory_id());
				insertFac.setFac_connect_state(true);
				mapper.updateByPrimaryKeySelective(insertFac);
//				System.out.println("set连接中：：："+insertFac.getFactory_id());
			} else {
//				System.out.println("未连接：：："+factory.getFac_name());
				T_factory insertFac=new T_factory();
				insertFac.setFactory_id(factory.getFactory_id());
				insertFac.setFac_connect_state(false);
				mapper.updateByPrimaryKeySelective(insertFac);
//				System.out.println("set未连接：：："+insertFac.getFactory_id());
			}
		}
	}
}
