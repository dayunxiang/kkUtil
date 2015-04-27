package servic.inserterImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import servic.Inserter;
import util.BasicDataBaseConnection;
import vo.CountCFG;

public class RowUpData extends BasicDataBaseConnection implements Inserter {

	@Override
	public void insert(CountCFG cfg, double[][] ds) {
		Connection connection = getConnection(cfg.getResultConnectInfo());
		Statement statement = getStatement(connection);
		for (int i = 0; i < ds.length; i++) {
			String remark_text = cfg.getResultRemarkText();
			String insertSQL = null;
			if (remark_text == null)
				insertSQL = "UPDATE " + cfg.getResultTable() + " SET "
						+ cfg.getResultFiled().get(0) + "=" + ds[i][2]
						+ " WHERE " + cfg.getIdFiled() + "=" + ds[i][0]
						+ " AND " + cfg.getResultTimeFiled() + "=" + ds[i][1];
			else
				insertSQL = "UPDATE " + cfg.getResultTable() + " SET "
						+ cfg.getResultFiled().get(0) + "=" + ds[i][2]
						+ " WHERE " + cfg.getIdFiled() + "=" + ds[i][0]
						+ " AND " + cfg.getResultTimeFiled() + "=" + ds[i][1]
						+ " AND remark='" + remark_text + "'";
			try {
				statement.execute(insertSQL);
			} catch (SQLException e) {
				System.out.println("错误的结果：" + insertSQL);
				e.printStackTrace();
			}

		}
		closeConnection(connection, statement);
	}

}
