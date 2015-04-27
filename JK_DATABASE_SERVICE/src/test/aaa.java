package test;

import java.util.List;

import server.CountCFGArrayHandler;
import util.DataBaseConnectionLoadCfg;
import vo.CountCFG;

public class aaa {
	public static void main(String[] args) throws Exception {
		List<List<CountCFG>> theCFG = DataBaseConnectionLoadCfg
				.getDataBaseConnection("test");
		for (List<CountCFG> baseConnections : theCFG) {
			CountCFGArrayHandler arrayHandler = new CountCFGArrayHandler(
					baseConnections);
			new Thread(arrayHandler).start();
		}
	}
}
