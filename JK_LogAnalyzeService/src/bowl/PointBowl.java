package bowl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.T_monitorinfo;
import model.sys.T_factory;

public class PointBowl {
	private static volatile PointBowl bowl = null;
	private static Map<T_monitorinfo, T_factory> pointMap = new HashMap<>();
	private static Set<T_monitorinfo> pointSet = new HashSet<>();

	private PointBowl() {
	}

	public static PointBowl getInstance() {
		if (bowl == null)
			synchronized (FactorySettingBowl.class) {
				if (bowl == null) {
					bowl = new PointBowl();
				}
			}
		return bowl;
	}

	public void registerPoint(T_monitorinfo monitorinfo, T_factory factory) {
		pointMap.put(monitorinfo, factory);
		pointSet.add(monitorinfo);
	}

	public T_factory select(String pointName) {
		for (T_monitorinfo monitorinfo : pointSet) {
			if (monitorinfo.getProgram_id().equals(pointName)) {
				return pointMap.get(monitorinfo);
			}
		}
		return null;
	}
}
