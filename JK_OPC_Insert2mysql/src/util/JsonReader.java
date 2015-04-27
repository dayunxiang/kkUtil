package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import vo.Data;
import vo.Redis;

public class JsonReader {
	private String json;

	public JsonReader(String json) {
		this.json = json;
	}

	/*
	 * public List<GroupProperty> getGroupProperty() { this.json = "{\"qq\":[" +
	 * json + "]}"; ArrayList<GroupProperty> groups = new
	 * ArrayList<GroupProperty>(); new JSONObject(); JSONObject object1 =
	 * JSONObject.fromObject(json); JSONArray array =
	 * object1.getJSONArray("qq");
	 * 
	 * @SuppressWarnings("rawtypes") Map<String, Class> classMap = new
	 * HashMap<String, Class>(); classMap.put("cfg", ConnectionProperty.class);
	 * classMap.put("data", DataProperty.class);
	 * 
	 * for (int i = 0; i < array.size(); i++) { JSONObject object =
	 * array.getJSONObject(i); GroupProperty property = (GroupProperty)
	 * JSONObject.toBean(object, GroupProperty.class, classMap);
	 * groups.add(property); } return groups; }
	 */

	public Redis getRedis() {
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("data", Data.class);
		new JSONObject();
		JSONObject object1 = null;
		try {
			object1 = JSONObject.fromObject(json);
		} catch (Exception e) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH-mm-ss");
			LogWriter.writeRedisLog("得到无法解析的数据帧"
					+ dateFormat.format(new Date()) + " : " + json);
			return null;
		}
		Redis redis = (Redis) JSONObject.toBean(object1, Redis.class, classMap);
		return redis;
	}
}