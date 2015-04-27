package server;

import java.util.Timer;

public class Main {
	public static void main(String[] args) {
//		List<GroupProperty> groups = new ConfgReader(args[0]).getGroupPropertyList();
//		List<GroupProperty> groups = new ConfgReader("./cfg").getGroupPropertyList();
//		Thread thread = null;
//		new Timer().scheduleAtFixedRate(new RedisLoader(), 0, 10);
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(new GroupRun("./bksh"), 0, 3600000);
//		timer.scheduleAtFixedRate(new GroupRun(args[0]), 0, 3600000);
//		for (int i = 0; i < groups.size(); i++) {
//			thread = new Thread(new GroupRun(groups.get(i)));
//			thread.start();
//		}
	}
}
