package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Statement;

import vo.ConnectionProperty;

public class WriteNoConnectionLog implements Runnable {
	private ConnectionProperty property;
	private Statement statement;

	public WriteNoConnectionLog(ConnectionProperty property,
			Statement re) {
		this.property = property;
		this.statement = re;
		new Thread(this).start();
	}

	public void run() {
		write();
	}

	private void write() {
		String fileName = "../"+property.getBASE64();
		File file = new File(fileName);
		if (!file.exists()) {
			return;
		} else {
			String encoding = "GBK";
			try {
				if (file.isFile() && file.exists()) { // 判断文件是否存在
					InputStreamReader read = new InputStreamReader(
							new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						System.out.println(lineTxt);
						statement.execute(lineTxt.replaceAll("\r|\n", ""));
					}
					read.close();
					file.delete();
				} else {
					throw new RuntimeException("未找到需要读取的文件");
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
				return;
			}
		}
	}
}
