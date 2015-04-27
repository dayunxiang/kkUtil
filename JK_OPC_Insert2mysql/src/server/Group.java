package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RedisPool;
import util.LogWriter;
import util.TimeUtil;
import vo.ConnectionProperty;
import vo.GroupProperty;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class Group implements Runnable {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private GroupProperty property;
	private List<Connection> conns = new ArrayList<Connection>();
	private List<Statement> sts = new ArrayList<Statement>();
	private RedisPool pool = RedisPool.getRedisPool();

	private double no=Math.random();
	public Group(GroupProperty property) {
		System.out.println("新建了一个Group:"+no);
		this.property = property;
	}

	public void run() {
		System.out.println("start");
		start();
	}

	private void start() {
		for (int i = 0; i < property.getCfg().size(); i++) {
			getConn(property.getCfg().get(i));
		}
		for (int i = 0; i < sts.size(); i++) {
			String field = "";
			String value = "";
			List<String> id = new ArrayList<String>();
			for (int j = 0; j < property.getCfg().get(i).getData().size(); j++) {
				field += ","
						+ property.getCfg().get(i).getData().get(j).getField();
				id.add(property.getCfg().get(i).getData().get(j).getId());
			}
			System.out.println(i);
			for (int j = 0; j < id.size(); j++) {
				value += "," + pool.getDate(id.get(j)).getValue();
			}
			String sql = "INSERT INTO " + property.getCfg().get(i).getTable()
					+ "(" + property.getTime_field() + field + ") VALUES("
					+ TimeUtil.getNowTimeDouble() + "" + value + ")";
			if (sts.get(i) == null) {
				LogWriter.writeNoConnectionLog(property.getCfg().get(i), sql);
				continue;
			}
			try {
				System.out.println("sql语句：" + sql);
				sts.get(i).execute(sql);
			} catch (CommunicationsException e) {
				LogWriter.writeNoConnectionLog(property.getCfg().get(i), sql);
				System.out.println("连接中断" + property.getGroup_name());
				sts.set(i, null);
				continue;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeConn();
	}

	private void closeConn() {
		System.out.println("释放链接");
		for (Statement sta : sts) {
			if (sta == null)
				continue;
			else
				try {
					sta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		for (Connection conn : conns) {
			if (conn == null)
				continue;
			else
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		sts.clear();
		conns.clear();
	}

	private void getConn(ConnectionProperty property) {
		Statement re = null;
		Connection conn = null;
		String DB_URL = "jdbc:mysql://" + property.getHost() + ":"
				+ property.getPort() + "/" + property.getDatabase() + "?user="
				+ property.getUsername() + "&password=" + property.getPwd()
				+ "&useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		System.out.println("建立连接：" + DB_URL+"-----------"+no);
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL);
			re = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CommunicationsException e) {
			System.out.println("连接中断：" + this.property.getGroup_name());
		} catch (MySQLNonTransientConnectionException e) {
			System.out.println("多次连接中断：" + this.property.getGroup_name());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (re != null)
			new WriteNoConnectionLog(property, re);
		conns.add(conn);
		sts.add(re);
	}

}
