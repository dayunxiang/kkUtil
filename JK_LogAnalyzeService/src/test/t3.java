package test;

import service.LogReader;

public class t3 {
	public static void main(String[] args) {
		LogReader logReader=new LogReader();
		new Thread(logReader).start();
		
//		System.out.println("			Connection timed out".length());
	}
}
