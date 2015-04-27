package servic.countProcessImpl;

import java.util.Calendar;

import servic.Count;
import servic.CountProcess;
import servic.Inserter;
import servic.Selecter;
import servic.countImpl.RowCount;
import servic.inserterImpl.RowUpData;
import servic.selecterImpl.RowSelecter;
import vo.CountCFG;

public class RowCountProcess implements CountProcess, Runnable {

	private Selecter selecter;
	private Count count;
	private Inserter inserter;
	private CountCFG cfg;

	public RowCountProcess() {
		this.selecter = new RowSelecter();
		this.count = new RowCount();
		this.inserter = new RowUpData();
	}

	@Override
	public void run() {
		try {
			this.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(CountCFG cfg) {
		this.cfg = cfg;
	}

	public void count() throws Exception {
		Calendar calendar = Calendar.getInstance();
//		 calendar.setTimeInMillis(1419190182000l);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		while (calendar.get(Calendar.HOUR_OF_DAY) == hour) {
			long sTime = calendar.getTimeInMillis() / 1000;
			calendar.add(Calendar.MINUTE, cfg.getInterval());
			long eTime = calendar.getTimeInMillis() / 1000;
			double[][] dataSquare = selecter.select(cfg, sTime, eTime);
			double[][] result = count.count(dataSquare, cfg.getCountType());
			inserter.insert(cfg, result);
		}

	}

}
