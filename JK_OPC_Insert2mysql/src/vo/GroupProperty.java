package vo;

import java.util.List;

public class GroupProperty {
	private String group_name;
	private int time;
	private String time_field;
	private List<ConnectionProperty> cfg;

	public String getTime_field() {
		return time_field;
	}

	public void setTime_field(String time_field) {
		this.time_field = time_field;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<ConnectionProperty> getCfg() {
		return cfg;
	}

	public void setCfg(List<ConnectionProperty> cfg) {
		this.cfg = cfg;
	}

	public void setCfg(ConnectionProperty cfg) {
		this.cfg.add(cfg);
	}
}
