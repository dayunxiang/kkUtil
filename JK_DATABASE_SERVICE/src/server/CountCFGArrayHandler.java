package server;

import java.util.List;

import servic.CountProcess;
import servic.countProcessImpl.ColumnAcrossTableCountProcess;
import servic.countProcessImpl.ColumnCountProcess;
import servic.countProcessImpl.RowCountProcess;
import vo.CountCFG;

public class CountCFGArrayHandler implements Runnable {

	private List<CountCFG> cfgs;

	public CountCFGArrayHandler(List<CountCFG> cfgs) {
		this.cfgs = cfgs;
	}

	@Override
	public void run() {
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void execute() throws Exception {
		if (cfgs.size() == 1)
			for (int i = 0; i < cfgs.size(); i++) {
				switch (cfgs.get(i).getCFGType()) {
				case elementary_arithmetic_in_a_row: {
					CountProcess service = new RowCountProcess();
					service.initialize(cfgs.get(i));
					new Thread(service).start();
					break;
				}

				case arithmetic_in_a_column: {
					CountProcess service = new ColumnCountProcess();
					service.initialize(cfgs.get(i));
					new Thread(service).start();
					break;
				}

				case arithmetic_across_table: {
					CountProcess service = new ColumnAcrossTableCountProcess();
					service.initialize(cfgs.get(i));
					new Thread(service).start();
					break;
				}
				default:
					break;
				}
			}
		else
			for (int i = 0; i < cfgs.size(); i++) {
				switch (cfgs.get(i).getCFGType()) {
				case elementary_arithmetic_in_a_row: {
					System.out.println("row");
					CountProcess service = new RowCountProcess();
					service.initialize(cfgs.get(i));
					service.count();
					break;
				}

				case arithmetic_in_a_column: {
					System.out.println("column");
					CountProcess service = new ColumnCountProcess();
					service.initialize(cfgs.get(i));
					service.count();
					break;
				}

				case arithmetic_across_table: {
					System.out.println("column");
					CountProcess service = new ColumnAcrossTableCountProcess();
					service.initialize(cfgs.get(i));
					service.count();
					break;
				}
				default:
					break;
				}
			}
	}
}
