package servic.inserterImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import servic.Inserter;
import util.BasicDataBaseConnection;
import util.MyUtil;
import vo.CountCFG;

public class ColumnInserter extends BasicDataBaseConnection implements Inserter {

	@Override
	public void insert(CountCFG cfg, double[][] ds) {
		Connection connection = getConnection(cfg.getResultConnectInfo());
		Statement statement = getStatement(connection);
		checkRemarkField(cfg);
		for (int i = 0; i < ds.length; i++) {
			String remark_text = cfg.getResultRemarkText();
			String insertSQL = null;
			if (remark_text == null)
				insertSQL = "INSERT INTO " + cfg.getResultTable() + "("
						+ MyUtil.getString(cfg.getResultFiled()) + ") VALUES("
						+ MyUtil.getString(ds[i]) + ")";
			else
				insertSQL = "INSERT INTO " + cfg.getResultTable() + "("
						+ MyUtil.getString(cfg.getResultFiled())
						+ ",remark) VALUES(" + MyUtil.getString(ds[i]) + ",'"
						+ remark_text + "')";
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
