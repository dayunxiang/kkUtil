package vo;

import java.io.UnsupportedEncodingException;
import java.util.List;

import sun.misc.BASE64Encoder;

public class ConnectionProperty {
	private String host;
	private String port;
	private String username;
	private String pwd;
	private String database;
	private String table;
	private List<DataProperty> data;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<DataProperty> getData() {
		return data;
	}

	public void setData(List<DataProperty> data) {
		this.data = data;
	}

	public String getBASE64() {
		byte[] midbytes = null;
		String name = host + port + database + table;
		try {
			midbytes = name.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (new BASE64Encoder()).encodeBuffer(midbytes).replaceAll("\r|\n", "");
	}
}
