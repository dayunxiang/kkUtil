package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyUtil {

	public static String getLogTime(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String re=dateFormat.format(calendar.getTime());
		return re;
	}
}
