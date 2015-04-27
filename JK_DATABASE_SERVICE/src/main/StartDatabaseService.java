package main;

import java.util.Timer;

public class StartDatabaseService {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new ThreadManagment("test4"), 0, 3600000);
		// timer.schedule(new ThreadManagment(args[0]), 0, 3600000);
	}

}
