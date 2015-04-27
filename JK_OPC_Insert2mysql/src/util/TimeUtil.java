package util;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TimeUtil {

	public static ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(1);
	public static long getNowTimeDouble() {
		Date date = new Date();
		long re = date.getTime()/1000;
		return re;
	}
}
