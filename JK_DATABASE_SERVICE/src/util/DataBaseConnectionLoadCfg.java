package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import vo.CountCFG;
import vo.CountCFGimpl.CountAcrossTableColumn;
import vo.CountCFGimpl.CountInTable;
import vo.CountCFGimpl.DataBaseConnection;

public class DataBaseConnectionLoadCfg {

	protected static List<DataBaseConnection> getDataBaseConnection() {
		String cfgPath = "./cfg";
		List<DataBaseConnection> baseConnections = new ArrayList<DataBaseConnection>();
		File file = new File(cfgPath);
		try {
			if (file.isFile() && file.exists()) {
				InputStreamReader read;
				read = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					JSONObject jsonObject = JSON.parseObject(lineTxt);
					DataBaseConnection conn = JSON.toJavaObject(jsonObject,
							DataBaseConnection.class);
					baseConnections.add(conn);
				}
				read.close();
			} else {
				throw new RuntimeException("配置文件无法读取");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseConnections;
	}

	public static List<List<CountCFG>> getDataBaseConnection(String cfg) {
		String cfgPath = cfg;
		List<List<CountCFG>> re = new ArrayList<>();
		boolean isGroup = false;
		int groupNo = 0;
		File file = new File(cfgPath);
		try {
			if (file.isFile() && file.exists()) {
				InputStreamReader read;
				read = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.startsWith("#")) {
						continue;
					} else if (lineTxt.startsWith("@TYPE ONE") && !isGroup) {
						lineTxt = lineTxt.substring("@TYPE ONE".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						DataBaseConnection conn = JSON.toJavaObject(jsonObject,
								DataBaseConnection.class);
						List<CountCFG> cfgs = new ArrayList<>();
						cfgs.add(conn);
						re.add(cfgs);
					} else if (lineTxt.startsWith("@TYPE TWO") && !isGroup) {
						lineTxt = lineTxt.substring("@TYPE TWO".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						CountInTable countInTable = JSON.toJavaObject(
								jsonObject, CountInTable.class);
						List<CountCFG> cfgs = new ArrayList<>();
						cfgs.add(countInTable);
						re.add(cfgs);
					} else if (lineTxt.startsWith("@GROUP START") && !isGroup) {
						System.out.println("分组开始");
						isGroup = true;
					} else if (lineTxt.startsWith("@GROUP END") && isGroup) {
						System.out.println("分组结束");
						groupNo = 0;
						isGroup = false;
					} else if (lineTxt.startsWith("@TYPE ONE") && isGroup) {
						lineTxt = lineTxt.substring("@TYPE ONE".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						DataBaseConnection conn = JSON.toJavaObject(jsonObject,
								DataBaseConnection.class);
						if (groupNo == 0) {
							List<CountCFG> cfgs = new ArrayList<>();
							cfgs.add(conn);
							re.add(cfgs);
						} else
							re.get(re.size() - 1).add(conn);
						groupNo++;
					} else if (lineTxt.startsWith("@TYPE TWO") && isGroup) {
						lineTxt = lineTxt.substring("@TYPE TWO".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						CountInTable countInTable = JSON.toJavaObject(
								jsonObject, CountInTable.class);
						if (groupNo == 0) {
							List<CountCFG> cfgs = new ArrayList<>();
							cfgs.add(countInTable);
							re.add(cfgs);
						} else
							re.get(re.size() - 1).add(countInTable);
						groupNo++;
					} else if (lineTxt.startsWith("@TYPE THREE") && isGroup) {
						lineTxt = lineTxt.substring("@TYPE THREE".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						CountAcrossTableColumn countInTable = JSON
								.toJavaObject(jsonObject,
										CountAcrossTableColumn.class);
						if (groupNo == 0) {
							List<CountCFG> cfgs = new ArrayList<>();
							cfgs.add(countInTable);
							re.add(cfgs);
						} else
							re.get(re.size() - 1).add(countInTable);
						groupNo++;
					} else if (lineTxt.startsWith("@TYPE THREE") && !isGroup) {
						lineTxt = lineTxt.substring("@TYPE THREE".length());
						JSONObject jsonObject = JSON.parseObject(lineTxt);
						CountAcrossTableColumn conn = JSON.toJavaObject(
								jsonObject, CountAcrossTableColumn.class);
						List<CountCFG> cfgs = new ArrayList<>();
						cfgs.add(conn);
						re.add(cfgs);
					}
				}
				read.close();
			} else {
				throw new RuntimeException("配置文件无法读取");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("线程数：" + re.size());
		return re;

	}
}
