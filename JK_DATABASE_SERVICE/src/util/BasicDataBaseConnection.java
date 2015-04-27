package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import vo.ConnectInfo;
import vo.CountCFG;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class BasicDataBaseConnection {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	protected Connection getConnection(ConnectInfo connectInfo) {
		String DB_URL = "jdbc:mysql://" + connectInfo.getHost() + ":"
				+ connectInfo.getPort() + "/" + connectInfo.getDatabase()
				+ "?user=" + connectInfo.getUsername() + "&password="
				+ connectInfo.getPwd()
				+ "&useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(DB_URL);
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC加载失败：" + DRIVER_NAME);
			e.printStackTrace();
			return null;
		} catch (CommunicationsException e) {
			System.out.println("通信连接失败：host:" + connectInfo.getHost()
					+ ";port:" + connectInfo.getPort());
			return null;
		} catch (MySQLNonTransientConnectionException e) {
			System.out.println("通信连接异常");
			return null;
		} catch (SQLException e) {
			System.out.println("通信连接语句错误：" + DB_URL);
			e.printStackTrace();
			return null;
		}
	}

	protected Statement getStatement(Connection connection) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}

	protected void closeConnection(Connection connection, Statement statement) {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void checkRemarkField(CountCFG cfg) {
		String remark_field = cfg.getResultRemarkText();
		if (remark_field == null)
			return;
		else {
			String sql = "SELECT * FROM " + cfg.getResultTable()
					+ "  ORDER BY " + cfg.getResultTimeFiled()
					+ " DESC limit 1";
			try {
				Connection connection = getConnection(cfg
						.getResultConnectInfo());
				Statement statement = getStatement(connection);
				ResultSet resultSet = statement.executeQuery(sql);
				ResultSetMetaData metaData = resultSet.getMetaData();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					if (metaData.getColumnName(i).equals("remark")) {
						return;
					}
				}
				String addCoulmnSql = "alter table " + cfg.getResultTable()
						+ " add column remark varchar(25);";
				statement.execute(addCoulmnSql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
