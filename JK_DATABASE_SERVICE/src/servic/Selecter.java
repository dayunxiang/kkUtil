package servic;

import vo.CountCFG;

public interface Selecter {

	double[][] select(CountCFG cfg, double stime, double etime);

}
