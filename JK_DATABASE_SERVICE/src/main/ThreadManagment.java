package main;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import server.CountCFGArrayHandler;
import util.DataBaseConnectionLoadCfg;
import vo.CountCFG;

public class ThreadManagment extends TimerTask {
	private String path;

	// public ThreadManagment() {
	// baseConnections = DataBaseConnectionLoadCfg.getDataBaseConnection();
	// }

	public ThreadManagment(String path) {
		this.path = path;
	}

	public void run() {
		System.out.println();
		System.out.println(new Date().toString() + "重载配置文件：" + path);
		List<List<CountCFG>> theCFG = DataBaseConnectionLoadCfg
				.getDataBaseConnection(path);
		for (List<CountCFG> baseConnections : theCFG) {
			CountCFGArrayHandler arrayHandler = new CountCFGArrayHandler(
					baseConnections);
			new Thread(arrayHandler).start();
		}

	}
}
