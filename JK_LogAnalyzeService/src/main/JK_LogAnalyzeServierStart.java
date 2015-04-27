package main;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import service.ApplicationRegisterUtil;
import service.LogReader;
import service.RedisLogReader;
import service.StateSetter;

public class JK_LogAnalyzeServierStart {

	public static void main(String[] args) {
		System.out.println("加载所有厂区信息");
		ApplicationRegisterUtil.redistAllfactory();
		System.out.println("加载所有采集点信息");
		ApplicationRegisterUtil.redistAllPoint();
		

		System.out.println("开始计时服务");
		ScheduledThreadPoolExecutor se=new ScheduledThreadPoolExecutor(4);
//		se.scheduleAtFixedRate(new LogReader(), 0, 5, TimeUnit.MINUTES);
//		se.scheduleAtFixedRate(new StateSetter(), 5, 5, TimeUnit.MINUTES);
		se.scheduleAtFixedRate(new LogReader(), 0, 10, TimeUnit.SECONDS);
		se.scheduleAtFixedRate(new RedisLogReader(), 0, 10, TimeUnit.SECONDS);
		se.scheduleAtFixedRate(new StateSetter(), 10, 10, TimeUnit.SECONDS);
	}

}
