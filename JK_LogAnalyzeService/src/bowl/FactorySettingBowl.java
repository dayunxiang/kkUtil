package bowl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.sys.T_factory;

public class FactorySettingBowl {
	private static volatile FactorySettingBowl setting = null;
	private List<T_factory> factories = new ArrayList<T_factory>();
	private Map<Integer, Date> updateTimemap = new HashMap<Integer, Date>();

	private FactorySettingBowl() {
	}

	public static FactorySettingBowl getInstance() {
		if (setting == null)
			synchronized (FactorySettingBowl.class) {
				if (setting == null) {
					setting = new FactorySettingBowl();
				}
			}
		return setting;
	}

	public void Register(T_factory factory) {
		this.factories.add(factory);
	}

	public List<T_factory> getAllFactory() {
		return factories;
	}

	public void setState(int id) {
		for (T_factory factory : factories) {
			if (id == factory.getFactory_id()) {
				// factory.setFac_connect_state(false);
				updateTimemap.put(id, new Date());
			}
		}
	}

	public boolean getFactoryState(int facid) {
		Date date = updateTimemap.get(facid);
		if (date == null)
			return true;
		else {
			long now = new Date().getTime();
			long then = date.getTime();
//			System.out.println("now:"+now+"...then:"+then+"...sub:"+(now - then)+"...booean:"+((now - then) < 1 * 60 * 1000));
			return !((now - then) < 10 * 60 * 1000);
//			return !((now - then) < 5 * 60 * 1000);
		}
	}
}
