package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import bowl.FactorySettingBowl;
import bowl.PointBowl;
import util.MyUtil;

public class LogReader implements Runnable {
	 private static final String logPath="/mnt/opcInstert2mysql/";
//	private final String logPath = "E:/";
	private int lineNo = 0;
	private String encoding = "GBK";
	private String filePath = null;

	private void filepathchecker() throws IOException {
		if (filePath == null)
			filePath = logPath + "OutTimeNoConnection" + MyUtil.getLogTime();
		else if (filePath.equals(logPath + "OutTimeNoConnection"
				+ MyUtil.getLogTime()))
			return;
		else {
			readOnly();
			filePath = logPath + "OutTimeNoConnection" + MyUtil.getLogTime();
			lineNo = 0;
		}
	}

	private void read() throws IOException {
		filepathchecker();
		File file = new File(filePath);
		if (!file.exists())
			return;
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		int line = 0;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			line++;
			if (line > lineNo) {
				lineNo = line;
				analysis(lineTxt);
			}
		}
		read.close();
	}

	private void readOnly() throws IOException {
		File file = new File(filePath);
		if (!file.exists())
			return;
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		int line = 0;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			line++;
			if (line > lineNo) {
				lineNo = line;
				analysis(lineTxt);
			}
		}
		read.close();
	}

	private void analysis(String line) {
		String theMessage = line.substring(11);
		theMessage = theMessage.substring(0, theMessage.length() - 23);
		int factoryid = PointBowl.getInstance().select(theMessage)
				.getFactory_id();
		FactorySettingBowl.getInstance().setState(factoryid);
	}

	@Override
	public void run() {
		try {
			read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
