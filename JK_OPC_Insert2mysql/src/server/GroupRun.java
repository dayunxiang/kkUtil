package server;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.TimertaskPool;
import util.ConfgReader;
import vo.GroupProperty;

public class GroupRun extends TimerTask {

	private List<GroupProperty> propertys;
	private String cfgPath;
	private TimertaskPool pool = TimertaskPool.getTimertaskPool();
	private static ScheduledThreadPoolExecutor se ;
	
	public GroupRun(String cfgPath) {
		this.cfgPath = cfgPath;
	}

	private void getPropertys() {
		propertys = new ConfgReader(cfgPath).getGroupPropertyList();
	}

	public void run() {
		Timer timer=new Timer();
		timer.cancel();
		pool.cancelAll();
		se = new ScheduledThreadPoolExecutor(1);
		pool.in(se);
		getPropertys();
		for (int i = 0; i < propertys.size(); i++) {
			Group group = new Group(propertys.get(i));
			se.scheduleAtFixedRate(group, 0, propertys.get(i).getTime(), TimeUnit.MINUTES);
		}
	}

}
