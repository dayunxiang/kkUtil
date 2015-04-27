package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TimertaskPool {
	private List<ScheduledThreadPoolExecutor> pool = new ArrayList<ScheduledThreadPoolExecutor>();
	private static TimertaskPool timertaskPool;

	private TimertaskPool() {
	}

	public static synchronized TimertaskPool getTimertaskPool() {
		if (timertaskPool == null) {
			timertaskPool = new TimertaskPool();
		}
		return timertaskPool;
	}

	public void in(ScheduledThreadPoolExecutor task) {
		synchronized (pool) {
			pool.add(task);
		}
	}

	public void cancelAll() {
		System.out.println("清除所有定时任务重新加载");
		for (ScheduledThreadPoolExecutor i:pool) {
			i.shutdown();
		}
		pool.clear();
	}
}
