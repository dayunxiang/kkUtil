package vo.CountCFGimpl;

import java.util.ArrayList;
import java.util.List;

import enums.CFGType;
import enums.CountType;
import vo.ConnectInfo;
import vo.CountCFG;

public class CountInTable implements CountCFG {
	private final CFGType cfgType = CFGType.elementary_arithmetic_in_a_row;
	private String host;
	private int port;
	private String username;
	private String pwd;
	private String database;
	private String tablename;
	private String timefiled;
	private String idfiled;
	private String remark_text;
	private String active_filed;
	private String passive_filed;
	private String result_filed;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTimefiled() {
		return timefiled;
	}

	public void setTimefiled(String timefiled) {
		this.timefiled = timefiled;
	}

	public String getIdfiled() {
		return idfiled;
	}

	public void setIdfiled(String idfiled) {
		this.idfiled = idfiled;
	}

	public String getRemark_text() {
		return remark_text;
	}

	public void setRemark_text(String remark_text) {
		this.remark_text = remark_text;
	}

	public CFGType getCfgType() {
		return cfgType;
	}

	public String getActive_filed() {
		return active_filed;
	}

	public void setActive_filed(String active_filed) {
		this.active_filed = active_filed;
	}

	public String getPassive_filed() {
		return passive_filed;
	}

	public void setPassive_filed(String passive_filed) {
		this.passive_filed = passive_filed;
	}

	public String getResult_filed() {
		return result_filed;
	}

	public void setResult_filed(String result_filed) {
		this.result_filed = result_filed;
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
		return this.idfiled;
	}

	@Override
	public List<String> getSelectFiled() {
		List<String> re = new ArrayList<>();
		re.add(this.idfiled);
		re.add(this.timefiled);
		re.add(this.active_filed);
		re.add(this.passive_filed);
		return re;
	}

	@Override
	public List<CountType> getCountType() {
		List<CountType> re = new ArrayList<>();
		re.add(CountType.ID);
		re.add(CountType.TIME);
		switch (type) {
		case 0:// add
		{
			re.add(CountType.ADD);
			break;
		}
		case 1:// sub
		{
			re.add(CountType.SUB);
			break;
		}
		case 2:// mul
		{
			re.add(CountType.MUL);
			break;
		}
		case 3:// div
		{
			re.add(CountType.DIV);
			break;
		}

		default:
			break;
		}
		return re;
	}

	@Override
	public List<String> getResultFiled() {
		List<String> re = new ArrayList<>();
		re.add(this.result_filed);
		return re;
	}

	@Override
	public String getSelectTable() {
		return this.tablename;
	}

	@Override
	public String getResultTable() {
		return this.tablename;
	}

	@Override
	public int getInterval() {
		return 60;
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
		return this.remark_text;
	}

	@Override
	public String getResultRemarkText() {
		return this.remark_text;
	}

	@Override
	public void reSet() {
		throw new RuntimeException("FUCK YOU");
		}

}
