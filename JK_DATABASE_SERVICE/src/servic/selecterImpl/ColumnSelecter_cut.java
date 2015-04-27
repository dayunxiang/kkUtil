package servic.selecterImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import servic.Selecter;
import util.BasicDataBaseConnection;
import util.MyUtil;
import vo.CountCFG;

public class ColumnSelecter_cut extends BasicDataBaseConnection implements
		Selecter {

	@Override
	public double[][] select(CountCFG cfg, double stime, double etime) {
		Connection connection = getConnection(cfg.getSelectConnectInfo());
		Statement statement = getStatement(connection);
		List<String> sourceFiledNameList = cfg.getSelectFiled();
		String selectRemarkText = cfg.getSelecteRemarkText();
		String selectSQL;
		if (selectRemarkText == null)
			selectSQL = "SELECT " + MyUtil.getString(sourceFiledNameList)
					+ " FROM " + cfg.getSelectTable() + " WHERE "
					+ cfg.getSelectTimeFiled() + " < '" + stime + "' order by "
					+ cfg.getSelectTimeFiled() + " DESC limit 1";
		else
			selectSQL = "SELECT " + MyUtil.getString(sourceFiledNameList)
					+ " FROM " + cfg.getSelectTable() + " WHERE "
					+ cfg.getSelectTimeFiled() + " < '" + stime
					+ "' AND remark='" + selectRemarkText + "' order by "
					+ cfg.getSelectTimeFiled() + " DESC limit 1";
		int rowNo = 0;
		double[][] dataSquare = null;
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
		} finally {
			closeConnection(connection, statement);
		}
		return dataSquare;
	}

}
