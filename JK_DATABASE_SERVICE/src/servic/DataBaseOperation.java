package servic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.MyUtil;
import vo.CountCFGimpl.DataBaseConnection;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class DataBaseOperation implements Runnable {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	// private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	private DataBaseConnection config;
	private Connection connection;
	private Statement statement;
	private final int ERRORVALUE = -51520188;

	public DataBaseOperation(DataBaseConnection config) {
		this.config = config;
	}

	private void operation(Date date) {
		if (!getConn())
			return;
		List<String> sourceFiledNameList = MyUtil.getAllSourceFiledName(config);
		int[] typeList = MyUtil.getAllFiledType(config);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		while (calendar.get(Calendar.HOUR_OF_DAY) == hour) {
			long sTime = calendar.getTimeInMillis() / 1000;
			calendar.add(Calendar.MINUTE, config.getInterval());
			long eTime = calendar.getTimeInMillis() / 1000;
			String selectSQL = "SELECT " + config.getTimefiled() + ","
					+ MyUtil.getString(sourceFiledNameList) + " FROM "
					+ config.getTable_s() + " WHERE " + config.getTimefiled()
					+ " BETWEEN '" + sTime + "'AND'" + (eTime - 1)
					+ "' order by " + config.getTimefiled();
			int rowNo = 0;
			double[][] dataSquare;
			try {
				ResultSet resultSet = statement.executeQuery(selectSQL);
				resultSet.last();
				rowNo = resultSet.getRow();
				dataSquare = new double[sourceFiledNameList.size()][rowNo];
				resultSet.absolute(0);
				while (resultSet.next()) {
					for (int i = 0; i < sourceFiledNameList.size(); i++) {
						dataSquare[i][resultSet.getRow() - 1] = resultSet
								.getDouble(sourceFiledNameList.get(i));
					}
				}
			} catch (SQLException e) {
				System.out.println("错误的查询或结果：" + selectSQL);
				e.printStackTrace();
				return;
			}
			double[] data = new double[typeList.length];
			for (int i = 0; i < typeList.length; i++) {
				if (typeList[i] == 1) {// 平均
					int flg = 0;
					boolean isCon = false;
					double totle = 0;
					for (int j = 0; j < dataSquare[i].length; j++) {
						if (dataSquare[i][j] == ERRORVALUE)
							continue;
						else if (dataSquare[i][j] == 0) {
							isCon |= true;
							continue;
						} else {
							isCon |= true;
							totle += dataSquare[i][j];
							flg++;
						}
					}
					double eva = 0;
					if (isCon) {
						eva = totle / flg;
						if (Double.isNaN(eva))
							eva = 0;
					} else {
						eva = ERRORVALUE;
					}
					data[i] = eva;
				} else if (typeList[i] == 2) {// 求和
					double totle = 0;
					boolean flg = false;
					for (int j = 0; j < dataSquare[i].length; j++) {
						if (dataSquare[i][j] == ERRORVALUE)
							continue;
						else {
							flg |= true;
							totle += dataSquare[i][j];
						}
					}
					if (flg)
						data[i] = totle * config.getData().get(i).getInterval();
					else
						data[i] = ERRORVALUE;
				} else if (typeList[i] == 0) {// 保留
					data[i] = dataSquare[i][dataSquare[i].length - 1];
					for (double value : dataSquare[i]) {
						data[i] = data[i] < value ? value : data[i];
					}
				} else if (typeList[i] == 3) {// 求这个小时的最后一条数与上一个小时最后一条数的差值
					double thistime = dataSquare[i][dataSquare[i].length - 1];
					double lasttime = searchLastValue(
							sourceFiledNameList.get(i), sTime);
					if (Double.isInfinite(lasttime) || Double.isNaN(lasttime))
						data[i] = 0;
					else
						data[i] = thistime - lasttime;
				} else if (typeList[i] == 4) {// 求变化率 （值-值）/（时间-时间）
					if (rowNo != 1 && rowNo != 0) {
						double first = dataSquare[i][0];
						double last = dataSquare[i][dataSquare[i].length - 1];
						double time = eTime - sTime;
						data[i] = (last - first) / time;
					} else
						data[i] = 0;
				}else{
					data[i] = ERRORVALUE;
				}
			}
			checkRemarkField();
			String remark_text = config.getRemark_text();
			String insertSQL = null;
			if (remark_text == null)
				insertSQL = "INSERT INTO "
						+ config.getTable_d()
						+ "("
						+ config.getTimefiled()
						+ ","
						+ MyUtil.getString(MyUtil
								.getAllPurposeFiledName(config)) + ") VALUES('"
						+ (sTime + config.getInterval() * 60) + "',"
						+ MyUtil.getString(data) + ")";
			else
				insertSQL = "INSERT INTO "
						+ config.getTable_d()
						+ "("
						+ config.getTimefiled()
						+ ","
						+ MyUtil.getString(MyUtil
								.getAllPurposeFiledName(config))
						+ ",remark) VALUES('"
						+ (sTime + config.getInterval() * 60) + "',"
						+ MyUtil.getString(data) + ",'" + remark_text + "')";
			try {
				statement.execute(insertSQL);
			} catch (SQLException e) {
				System.out.println("错误的结果：" + insertSQL);
				e.printStackTrace();
			}

		}
		closeConnection();
	}

	private double searchLastValue(String valueField, double endtime) {
		String searchSql = "SELECT " + valueField + " FROM "
				+ config.getTable_s() + " WHERE " + config.getTimefiled() + "<"
				+ endtime + " ORDER BY " + config.getTimefiled()
				+ " DESC limit 1";
		try {
			ResultSet resultSet = statement.executeQuery(searchSql);
			while (resultSet.next()) {
				return resultSet.getDouble(valueField);
			}
		} catch (SQLException e) {
			System.out.println("错误的查询或结果：" + searchSql);
			e.printStackTrace();
			return Double.NaN;
		}
		return Double.NaN;
	}

	private void checkRemarkField() {
		String remark_field = config.getRemark_text();
		if (remark_field == null)
			return;
		else {
			String sql = "SELECT * FROM " + config.getTable_d() + "  ORDER BY "
					+ config.getTimefiled() + " DESC limit 1";
			try {
				ResultSet resultSet = statement.executeQuery(sql);
				ResultSetMetaData metaData = resultSet.getMetaData();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					if (metaData.getColumnName(i).equals("remark")) {
						return;
					}
				}
				String addCoulmnSql = "alter table " + config.getTable_d()
						+ " add column remark varchar(25);";
				statement.execute(addCoulmnSql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// private void operationByGroup(int year, int month, int date, int hour24)
	// {
	// if (!getConn())
	// return;
	// String sTime = getThisHourStart_g(year, month, date, hour24);
	// String eTime = getNextHourStart_g(year, month, date, hour24);
	// List<String> filedlist = MyUtil.getAllFiledName(config);
	// // int[] typeList = MyUtil.getAllFiledType(config);
	// String selectSQL = "SELECT " + config.getTimefiled() + ","
	// + config.getGroupfeild() + " , " + MyUtil.getString(filedlist)
	// + " FROM " + config.getTable_s() + " WHERE "
	// + config.getTimefiled() + " BETWEEN '" + sTime + "'AND'"
	// + eTime + "' order by " + config.getTimefiled();
	// try {
	// ResultSet resultSet = statement.executeQuery(selectSQL);
	// Map<String, Map<String, ArrayList<Double>>> dataMap = new HashMap<>();
	// while (resultSet.next()) {
	// String groupName = resultSet.getString(config.getGroupfeild());
	// if (dataMap.get(groupName) == null) {
	// Map<String, ArrayList<Double>> feild = new HashMap<String,
	// ArrayList<Double>>();
	// for (int i = 0; i < filedlist.size(); i++) {
	// ArrayList<Double> a = new ArrayList<>();
	// feild.put(filedlist.get(i), a);
	// }
	// dataMap.put(groupName, feild);
	// }
	// for (int i = 0; i < filedlist.size(); i++) {
	// dataMap.get(groupName).get(filedlist.get(i))
	// .add(resultSet.getDouble(filedlist.get(i)));
	// }
	// }
	// Iterator<String> allGroupName = dataMap.keySet().iterator();
	// while (allGroupName.hasNext()) {
	// String groupName = allGroupName.next();
	// List<Double> data = new ArrayList<>();
	// for (int i = 0; i < filedlist.size(); i++) {
	// List<Double> values = dataMap.get(groupName).get(
	// filedlist.get(i));
	// switch (MyUtil.getFieldType(config, filedlist.get(i))) {
	// case 1: {// 平均
	// data.add(MyUtil.getListEve(values));
	// break;
	// }
	// case 2: {// 求和
	// data.add(MyUtil.getListAdd(values)
	// * MyUtil.getFieldInterval(config,
	// filedlist.get(i)));
	// break;
	// }
	// case 0: {// 保留
	// data.add(MyUtil.getListLast(values));
	// break;
	// }
	//
	// default: {
	// data.add(null);
	// break;
	// }
	// }
	// }
	// String insertSQL = "INSERT INTO " + config.getTable_d() + "("
	// + config.getTimefiled() + "," + config.getGroupfeild()
	// + "," + MyUtil.getString(filedlist) + ") VALUES('"
	// + sTime + "','" + groupName + "',"
	// + MyUtil.getDouble2String(data) + ")";
	// statement.execute(insertSQL);
	// }
	// } catch (SQLException e) {
	// System.out.println("错误的查询或结果：" + selectSQL);
	// e.printStackTrace();
	// return;
	// }
	// closeConnection();
	// }

	private boolean getConn() {
		String DB_URL = "jdbc:mysql://" + config.getHost() + ":"
				+ config.getPort() + "/" + config.getDatabase() + "?user="
				+ config.getUsername() + "&password=" + config.getPwd()
				+ "&useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		System.out.print("连接数据库：" + config.getDatabase() + "\u2580");
		try {
			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC加载失败：" + DRIVER_NAME);
			e.printStackTrace();
			return false;
		} catch (CommunicationsException e) {
			System.out.println("通信连接失败：host:" + config.getHost() + ";port:"
					+ config.getPort());
			return false;
		} catch (MySQLNonTransientConnectionException e) {
			System.out.println("通信连接异常");
			return false;
		} catch (SQLException e) {
			System.out.println("通信连接语句错误：" + DB_URL);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// private String getThisHourStart_g(int year, int month, int date, int
	// hour24) {
	// Calendar calendar = Calendar.getInstance();
	// calendar.set(year, month, date, hour24, 0, 0);
	// return DATE_FORMAT.format(calendar.getTime());
	// }
	//
	// private String getNextHourStart_g(int year, int month, int date, int
	// hour24) {
	// Calendar calendar = Calendar.getInstance();
	// calendar.set(year, month, date, hour24 + 1, 0, -1);
	// return DATE_FORMAT.format(calendar.getTime());
	// }

	public void run() {
		// if (config.getGroupfeild() == null)
		operation(new Date());
		// else
		// operationByGroup(Calendar.getInstance().get(Calendar.YEAR),
		// Calendar.getInstance().get(Calendar.MONTH), Calendar
		// .getInstance().get(Calendar.DAY_OF_MONTH), Calendar
		// .getInstance().get(Calendar.HOUR_OF_DAY) - 1);

	}

}
