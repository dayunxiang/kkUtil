package servic.selecterImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import servic.Selecter;
import servic.countImpl.ColumnCount;
import util.BasicDataBaseConnection;
import util.MyUtil;
import vo.ConnectInfo;
import vo.CountCFG;

public class ColumnAcrossTableSelecter extends BasicDataBaseConnection
		implements Selecter {

	@Override
	public double[][] select(CountCFG cfg, double stime, double etime) {
		double[][] r = new double[1][cfg.getResultFiled().size()];
		ColumnCount cc = new ColumnCount();
		// [time][feild1][feild2][feild3][feild4][feild5]
		ConnectInfo connectInfo = cfg.getSelectConnectInfo();
		int flg = 1;
		// 单个feild查询
		do {
			Connection connection = getConnection(connectInfo);
			Statement statement = getStatement(connection);
			List<String> sourceFiledNameList = cfg.getSelectFiled();
			String selectTable = cfg.getSelectTable();
			String selectTimeFiled = cfg.getSelectTimeFiled();
			String selectRemarkText = cfg.getSelecteRemarkText();
			String selectSQL;
			if (selectRemarkText == null)
				selectSQL = "SELECT " + MyUtil.getString(sourceFiledNameList)
						+ " FROM " + selectTable + " WHERE " + selectTimeFiled
						+ " BETWEEN '" + stime + "'AND'" + (etime - 1)
						+ "' order by " + selectTimeFiled;
			else
				selectSQL = "SELECT " + MyUtil.getString(sourceFiledNameList)
						+ " FROM " + selectTable + " WHERE " + selectTimeFiled
						+ " BETWEEN '" + stime + "'AND'" + (etime - 1)
						+ "' AND remark='" + selectRemarkText + "' order by "
						+ selectTimeFiled;
			int rowNo = 0;
			double[][] dataSquare = null;
			try {
				ResultSet resultSet = statement.executeQuery(selectSQL);
				resultSet.last();
				rowNo = resultSet.getRow();
				dataSquare = new double[sourceFiledNameList.size()][rowNo + 1];
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
			} finally {
				closeConnection(connection, statement);
			}

			// 上一条查询
			connection = getConnection(connectInfo);
			statement = getStatement(connection);
			String cutselectSQL;
			if (selectRemarkText == null)
				cutselectSQL = "SELECT "
						+ MyUtil.getString(sourceFiledNameList) + " FROM "
						+ selectTable + " WHERE " + selectTimeFiled + " < '"
						+ stime + "' order by " + selectTimeFiled
						+ " DESC limit 1";
			else
				cutselectSQL = "SELECT "
						+ MyUtil.getString(sourceFiledNameList) + " FROM "
						+ selectTable + " WHERE " + selectTimeFiled + " < '"
						+ stime + "' AND remark='" + selectRemarkText
						+ "' order by " + selectTimeFiled + " DESC limit 1";

			int cutrowNo = 0;
			double[][] cutdataSquare = null;
			try {
				ResultSet resultSet = statement.executeQuery(cutselectSQL);
				resultSet.last();
				cutrowNo = resultSet.getRow();
				System.out.println(sourceFiledNameList.size() + "   "
						+ cutrowNo);
				cutdataSquare = new double[sourceFiledNameList.size()][cutrowNo];
				resultSet.absolute(0);
				while (resultSet.next()) {
					for (int i = 0; i < sourceFiledNameList.size(); i++) {
						cutdataSquare[i][resultSet.getRow() - 1] = resultSet
								.getDouble(sourceFiledNameList.get(i));
					}
				}
			} catch (SQLException e) {
				System.out.println("错误的查询或结果：" + selectSQL);
				e.printStackTrace();
			} finally {
				closeConnection(connection, statement);
			}

			// 把上个小时最后一条插入
			if (cutdataSquare[0].length != 0)
				for (int i = 0; i < cutdataSquare.length; i++) {
					dataSquare[i][rowNo] = cutdataSquare[i][0];
				}
			else
				for (int i = 0; i < cutdataSquare.length; i++) {
					dataSquare[i][rowNo] = 0;
				}

			// 立刻计算
			double[][] onefeild = null;
			try {
				onefeild = cc.count(dataSquare, cfg.getCountType());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(flg + "次查询结果");
			MyUtil.outTwoDimensionArray(onefeild);

			// 计算结果写入返回
			if (onefeild != null)
				r[0][flg] = onefeild[0][1];
			else
				r[0][flg] = 0;
			flg++;

			connectInfo = cfg.getSelectConnectInfo();
		} while (connectInfo != null);
		return r;
	}

}
