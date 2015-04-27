package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.sys.T_factory;
import redis.RedisClient;
import util.MyUtil;
import bowl.PointBowl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RedisLogReader implements Runnable {
	 private static final String logPath = "/mnt/opcInstert2mysql/";
//	private final String logPath = "E:/";
	private int lineNo = 0;
	private String encoding = "GBK";
	private String filePath = null;
	private RedisClient client = new RedisClient();

	private void filepathchecker() throws IOException {
		if (filePath == null)
			filePath = logPath + "log_Redis " + MyUtil.getLogTime();
		else if (filePath.equals(logPath + "log_Redis " + MyUtil.getLogTime()))
			return;
		else {
			readOnly();
			filePath = logPath + "log_Redis " + MyUtil.getLogTime();
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
		if (line.length() < "2015-02-11 00-00-12 : ".length())
			return;
		String theMessage = line.substring("2015-02-11 00-00-12 : ".length());
		JSONObject jsonObject = JSON.parseObject(theMessage);
		JSONArray array = jsonObject.getJSONArray("data");
		String oneMonitorName = array.getJSONObject(0).getString("id");
		 T_factory factory = PointBowl.getInstance().select(oneMonitorName);
		 if (factory == null)
		 return;
		 int factoryid = factory.getFactory_id();
		 client.addCounter(String.valueOf(factoryid));
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
