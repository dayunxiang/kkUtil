package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vo.ConnectionProperty;

public class LogWriter {

	public LogWriter() {
	}

	public static void writeRedisLog(String massage) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String redisLogFilePath = "../log_Redis";
		String redisLogPath = redisLogFilePath + " "
				+ dateFormat.format(new Date());
		File redisLog = new File(redisLogPath);
		check(redisLog);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(redisLog, true);
			fileWriter.write(massage + "\n");
			fileWriter.write("\r\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeNoConnectionLog(
			ConnectionProperty connectionProperty, String SQL) {
		String groupLogFileName = "../" + connectionProperty.getBASE64();
		File groupLog = new File(groupLogFileName);
		check(groupLog);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(groupLog, true);
			System.out.println("¼����־��" + groupLogFileName + "�����" + SQL);
			fileWriter.write(SQL + "\n");
			// fileWriter.write("\r\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeConnectionOutTime(String id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat_d = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String groupLogFileName = "../OutTimeNoConnection"
				+ dateFormat.format(now);
		File groupLog = new File(groupLogFileName);
		check(groupLog);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(groupLog, true);
			fileWriter.write(dateFormat_d.format(now) + "\t\t\t" + id
					+ "\t\t\t" + "Connection timed out" + "\n");
			// fileWriter.write("\r\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public static void writeRedisNoConnectionLog( String value) {
	// String redisLogFileName = "../redisLog";
	// File groupLog = new File(redisLogFileName);
	// // System.out.println(groupLogFileName);
	// check(groupLog);
	// FileWriter fileWriter;
	// try {
	// fileWriter = new FileWriter(groupLog, true);
	// fileWriter.write(value + "\n");
	// // fileWriter.write("\r\n");
	// fileWriter.flush();
	// fileWriter.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	private static void check(File file) {
		if (!file.isFile())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
