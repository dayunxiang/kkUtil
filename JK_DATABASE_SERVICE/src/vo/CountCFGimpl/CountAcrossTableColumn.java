package vo.CountCFGimpl;

import java.util.ArrayList;
import java.util.List;

import vo.ConnectInfo;
import vo.CountCFG;
import vo.CountCFGimpl.dependency.DataSource;
import enums.CFGType;
import enums.CountType;

public class CountAcrossTableColumn implements CountCFG {
	private final CFGType cfgType = CFGType.arithmetic_across_table;
	private int interval;// 计算间隔
	private String targetHost;// 目标地址
	private int targetPort;// 目标端口
	private String targetUsername;// 目标数据库用户名
	private String targetPwd;// 目标数据库密码
	private String targetDatabase;// 目标数据库
	private String targetTable;// 目标表
	private String targetRemarkText;// 计算出来后的remark（可为空）
	private String timeFeild;
	private List<DataSource> data;// 计算数据来源

	private int connectInfoFLG = 0;
	private int selectTableFLG = 0;
	private int selectFeildFLG = 0;
	private int selectTimeFeildGLF = 0;
	private int selectRemarkTextGLF = 0;
	private int countTypeFLG = 0;

	public String getTimeFeild() {
		return timeFeild;
	}

	public void setTimeFeild(String timeFeild) {
		this.timeFeild = timeFeild;
	}

	public String getTargetHost() {
		return targetHost;
	}

	public void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}

	public int getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

	public String getTargetPwd() {
		return targetPwd;
	}

	public void setTargetPwd(String targetPwd) {
		this.targetPwd = targetPwd;
	}

	public String getTargetDatabase() {
		return targetDatabase;
	}

	public void setTargetDatabase(String targetDatabase) {
		this.targetDatabase = targetDatabase;
	}

	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	public String getTargetRemarkText() {
		return targetRemarkText;
	}

	public void setTargetRemarkText(String targetRemarkText) {
		this.targetRemarkText = targetRemarkText;
	}

	public List<DataSource> getData() {
		return data;
	}

	public void setData(List<DataSource> data) {
		this.data = data;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public CFGType getCFGType() {
		return this.cfgType;
	}

	@Override
	public String getIdFiled() {
		return null;
	}

	@Override
	public ConnectInfo getSelectConnectInfo() {
		int connecSize = this.data.size();
		if (this.connectInfoFLG < connecSize) {
			DataSource dataSource = this.data.get(this.connectInfoFLG);
			ConnectInfo info = new ConnectInfo();
			info.setDatabase(dataSource.getSourceDatabase());
			info.setHost(dataSource.getSourceHost());
			info.setPort(dataSource.getSourcePort());
			info.setPwd(dataSource.getSourcePwd());
			info.setUsername(dataSource.getSourceUsername());
			this.connectInfoFLG++;
			return info;
		} else if (this.connectInfoFLG == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}
	}

	@Override
	public String getSelectTable() {
		int connecSize = this.data.size();
		if (this.selectTableFLG < connecSize) {
			DataSource dataSource = this.data.get(this.selectTableFLG);
			this.selectTableFLG++;
			return dataSource.getSourceTable();
		} else if (this.selectTableFLG == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}
	}

	@Override
	public List<String> getSelectFiled() {
		int connecSize = this.data.size();
		if (this.selectFeildFLG < connecSize) {
			DataSource dataSource = this.data.get(this.selectFeildFLG);
			this.selectFeildFLG++;
			List<String> re = new ArrayList<>();
			re.add(dataSource.getTimeFeild());
			re.add(dataSource.getSourceFeild());
			return re;
		} else if (this.selectFeildFLG == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}
	}

	@Override
	public List<CountType> getCountType() {
		List<CountType> countTypes = new ArrayList<>();
		countTypes.add(CountType.TIME);
		int connecSize = this.data.size();
		if (this.countTypeFLG < connecSize) {
			DataSource dataSource = this.data.get(this.countTypeFLG);
			this.countTypeFLG++;
			int i = dataSource.getType();
			if (i > 4)
				countTypes.add(CountType.TIME);
			else if (i != 2)
				countTypes.add(CountType.values()[i]);
			else {
				CountType countType = CountType.CUMULATIVE;
				countType.setInterval(interval);
				countTypes.add(countType);
			}
		} else if (this.countTypeFLG == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}

		return countTypes;
	}

	@Override
	public ConnectInfo getResultConnectInfo() {
		ConnectInfo info = new ConnectInfo();
		info.setDatabase(this.getTargetDatabase());
		info.setHost(this.getTargetHost());
		info.setPort(this.getTargetPort());
		info.setPwd(this.getTargetPwd());
		info.setUsername(this.getTargetUsername());
		return info;
	}

	@Override
	public String getResultTable() {
		return this.targetTable;
	}

	@Override
	public List<String> getResultFiled() {
		List<String> re = new ArrayList<>();
		re.add(this.timeFeild);
		for (DataSource dataSource : this.data) {
			re.add(dataSource.getTargetFeild());
		}
		return re;
	}

	@Override
	public int getInterval() {
		return this.interval;
	}

	@Override
	public String getSelectTimeFiled() {
		int connecSize = this.data.size();
		if (this.selectTimeFeildGLF < connecSize) {
			DataSource dataSource = this.data.get(this.selectTimeFeildGLF);
			this.selectTimeFeildGLF++;
			return dataSource.getTimeFeild();
		} else if (this.selectTimeFeildGLF == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}
	}

	@Override
	public String getResultTimeFiled() {
		return this.timeFeild;
	}

	@Override
	public String getSelecteRemarkText() {
		int connecSize = this.data.size();
		if (this.selectRemarkTextGLF < connecSize) {
			DataSource dataSource = this.data.get(this.selectRemarkTextGLF);
			this.selectRemarkTextGLF++;
			return dataSource.getSourceRemarkText();
		} else if (this.selectRemarkTextGLF == connecSize)
			return null;
		else {
			throw new RuntimeException("");
		}
	}

	@Override
	public String getResultRemarkText() {
		return this.targetRemarkText;
	}

	@Override
	public void reSet() {
		this.connectInfoFLG = 0;
		this.selectTableFLG = 0;
		this.selectFeildFLG = 0;
		this.selectTimeFeildGLF = 0;
		this.selectRemarkTextGLF = 0;
		this.countTypeFLG = 0;
	}

}
