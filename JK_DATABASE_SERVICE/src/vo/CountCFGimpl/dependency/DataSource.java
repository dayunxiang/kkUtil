package vo.CountCFGimpl.dependency;

public class DataSource {
	private String sourceHost;// 来源地址
	private int sourcePort;// 来源端口
	private String sourceUsername;// 来源数据库用户名
	private String sourcePwd;// 密码
	private String sourceDatabase;// 数据库
	private String sourceTable;// 表名
	private String sourceFeild;// 字段名
	private String sourceRemarkText;// 筛选条件（可选）
	private String timeFeild;
	private String targetFeild;// 目标字段名
	private int type;// 计算方法

	public String getTimeFeild() {
		return timeFeild;
	}

	public void setTimeFeild(String timeFeild) {
		this.timeFeild = timeFeild;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSourceHost() {
		return sourceHost;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public int getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getSourceUsername() {
		return sourceUsername;
	}

	public void setSourceUsername(String sourceUsername) {
		this.sourceUsername = sourceUsername;
	}

	public String getSourcePwd() {
		return sourcePwd;
	}

	public void setSourcePwd(String sourcePwd) {
		this.sourcePwd = sourcePwd;
	}

	public String getSourceDatabase() {
		return sourceDatabase;
	}

	public void setSourceDatabase(String sourceDatabase) {
		this.sourceDatabase = sourceDatabase;
	}

	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public String getSourceFeild() {
		return sourceFeild;
	}

	public void setSourceFeild(String sourceFeild) {
		this.sourceFeild = sourceFeild;
	}

	public String getSourceRemarkText() {
		return sourceRemarkText;
	}

	public void setSourceRemarkText(String sourceRemarkText) {
		this.sourceRemarkText = sourceRemarkText;
	}

	public String getTargetFeild() {
		return targetFeild;
	}

	public void setTargetFeild(String targetFeild) {
		this.targetFeild = targetFeild;
	}

}
