package servic;

import vo.CountCFG;

public interface CountProcess extends Runnable {

	void initialize(CountCFG cfg);

	void count() throws Exception;
}