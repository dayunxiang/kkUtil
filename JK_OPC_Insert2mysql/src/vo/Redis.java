package vo;

import java.util.List;

public class Redis {
	private long time;
	private List<Data> data;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

//	public String getJson() {
//		JsonConfig config=new JsonConfig();
//		Map<String, Class> classMap = new HashMap<String, Class>();
//		classMap.put("data", Data.class);
////		config.setClassMap(classMap);
//		config.findJsonBeanProcessor(Data.class);
//		JSONObject object1 = JSONObject.fromObject(this,config);
//		return object1.toString();
//	}
}
