package vo.CountCFGimpl;

import java.util.ArrayList;
import java.util.List;

import enums.CFGType;
import enums.CountType;
import util.MyUtil;
import vo.ConnectInfo;
import vo.CountCFG;
import vo.CountCFGimpl.dependency.Feild;

public class DataBaseConnection implements CountCFG {
	private final CFGType cfgType = CFGType.arithmetic_in_a_column;
	private String host;
	private int port;
	private String username;
	private String pwd;
	private String database;
	private String table_s;// tablesource 资源表
	private String table_d;// 目标表
	private String timefiled;// 时间字段名
	private int interval;// 计算间隔
	private List<Feild> data;// 数据信息，计算信息
	private String remark_text;// 如果不为NULL,结果会标注remark
	private String selectRemarkText;// 如果不为NULL，则查询时加入“remark=‘’”

	public String getSelectRemarkText() {
		return selectRemarkText;
	}

	public void setSelectRemarkText(String selectRemarkText) {
		this.selectRemarkText = selectRemarkText;
	}

	public String getRemark_text() {
		return remark_text;
	}

	public void setRemark_text(String remark_text) {
		this.remark_text = remark_text;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTable_s() {
		return table_s;
	}

	public void setTable_s(String table_s) {
		this.table_s = table_s;
	}

	public String getTable_d() {
		return table_d;
	}

	public void setTable_d(String table_d) {
		this.table_d = table_d;
	}

	public List<Feild> getData() {
		return data;
	}

	public void setData(List<Feild> data) {
		this.data = data;
	}

	public String getTimefiled() {
		return timefiled;
	}

	public void setTimefiled(String timefiled) {
		this.timefiled = timefiled;
	}

	@Override
	public ConnectInfo getSelectConnectInfo() {
		ConnectInfo connectInfo = new ConnectInfo();
		connectInfo.setDatabase(this.database);
		connectInfo.setHost(this.host);
		connectInfo.setPort(this.port);
		connectInfo.setPwd(this.pwd);
		connectInfo.setUsername(this.username);
		return connectInfo;
	}

	@Override
	public ConnectInfo getResultConnectInfo() {
		ConnectInfo connectInfo = new ConnectInfo();
		connectInfo.setDatabase(this.database);
		connectInfo.setHost(this.host);
		connectInfo.setPort(this.port);
		connectInfo.setPwd(this.pwd);
		connectInfo.setUsername(this.username);
		return connectInfo;
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
	public List<String> getSelectFiled() {
		List<String> sourceFiledNameList = MyUtil.getAllSourceFiledName(this);
		return sourceFiledNameList;
	}

	@Override
	public List<CountType> getCountType() {
		int[] typeList = MyUtil.getAllFiledType(this);
		List<CountType> countTypes = new ArrayList<>();
		countTypes.add(CountType.TIME);
		for (int j = 0; j < typeList.length; j++) {
			int i = typeList[j];
			if (i > 4)
				countTypes.add(CountType.TIME);
			else if (i != 2)
				countTypes.add(CountType.values()[i]);
			else {
				CountType countType = CountType.CUMULATIVE;
				countType.setInterval(this.data.get(j).getInterval());
				countTypes.add(countType);
			}
		}
		return countTypes;
	}

	@Override
	public List<String> getResultFiled() {
		List<String> re = MyUtil.getAllPurposeFiledName(this);
		re.add(0, timefiled);
		return re;
	}

	@Override
	public String getSelectTable() {
		return this.table_s;
	}

	@Override
	public String getResultTable() {
		return this.table_d;
	}

	@Override
	public String getSelectTimeFiled() {
		return this.timefiled;
	}

	@Override
	public String getResultTimeFiled() {
		return this.timefiled;
	}

	@Override
	public String getSelecteRemarkText() {
		return this.selectRemarkText;
	}

	@Override
	public String getResultRemarkText() {
		return this.remark_text;
	}

	@Override
	public void reSet() {
		throw new RuntimeException();
	}

}
