package servic.countProcessImpl;

import java.util.Calendar;

import servic.CountProcess;
import servic.Inserter;
import servic.Selecter;
import servic.inserterImpl.ColumnInserter;
import servic.selecterImpl.ColumnAcrossTableSelecter;
import vo.CountCFG;

public class ColumnAcrossTableCountProcess implements CountProcess {

	private Selecter selecter;
	private Inserter inserter;
	private CountCFG cfg;

	public ColumnAcrossTableCountProcess() {
		this.selecter = new ColumnAcrossTableSelecter();
		this.inserter = new ColumnInserter();
	}

	@Override
	public void run() {
		try {
			this.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(CountCFG cfg) {
		this.cfg = cfg;
	}

	@Override
	public void count() throws Exception {
		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(1419190182000l);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		while (calendar.get(Calendar.HOUR_OF_DAY) == hour) {
			long sTime = calendar.getTimeInMillis() / 1000;
			calendar.add(Calendar.MINUTE, cfg.getInterval());
			long eTime = calendar.getTimeInMillis() / 1000;
			double[][] dataSquare = selecter.select(cfg, sTime, eTime);
			dataSquare[0][0] = sTime + cfg.getInterval() * 60;
			inserter.insert(cfg, dataSquare);
			cfg.reSet();
		}
	}

}
