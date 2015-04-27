package servic.countProcessImpl;

import java.util.Calendar;

import servic.Count;
import servic.CountProcess;
import servic.Inserter;
import servic.Selecter;
import servic.countImpl.ColumnCount;
import servic.inserterImpl.ColumnInserter;
import servic.selecterImpl.ColumnSelecter;
import vo.CountCFG;

public class ColumnCountProcess implements CountProcess, Runnable {

	private Selecter selecter;
	private Count count;
	private Inserter inserter;
	private CountCFG cfg;

	public ColumnCountProcess() {
		this.selecter = new ColumnSelecter();
		this.count = new ColumnCount();
		this.inserter = new ColumnInserter();
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
		// calendar.setTimeInMillis(1419281191000l);
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
			result[0][0] = sTime + cfg.getInterval() * 60;
			inserter.insert(cfg, result);
		}

	}

}
