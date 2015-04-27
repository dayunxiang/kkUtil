package vo.CountCFGimpl.dependency;

public class Feild {
	private String source_field;//资源字段
	private String purpose_field;//目标字段
	private int type;//计算类型
	private int interval = 0;//参数，累加用

	public int getType() {
		return type;
	}

	public String getSource_field() {
		return source_field;
	}

	public void setSource_field(String source_field) {
		this.source_field = source_field;
	}

	public String getPurpose_field() {
		return purpose_field;
	}

	public void setPurpose_field(String purpose_field) {
		this.purpose_field = purpose_field;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

}
