package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.ConfgReader;
import vo.ConnectionProperty;
import vo.DataProperty;
import vo.GroupProperty;

public class test1 {

	public static void main(String[] args) {
		String path = "./tpx";
		ConfgReader reader = new ConfgReader(path);
		String json = reader.readTxtFile();
		json = "{\"qq\":[" + json + "]}";
		new JSONObject();
		JSONObject object1 = JSONObject.fromObject(json);
		JSONArray array = object1.getJSONArray("qq");
		List<GroupProperty> list = new ArrayList<GroupProperty>();
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("cfg", ConnectionProperty.class);
		classMap.put("data", DataProperty.class);
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			GroupProperty property = (GroupProperty) JSONObject.toBean(object,
					GroupProperty.class,classMap);
//			System.out.println(property.getTime());
			list.add(property);
			// JSONArray conn = object.getJSONArray("cfg");
			// for(int j=0;j<conn.size();j++){
			// property.setCfg((ConnectionProperty)JSONObject.toBean(conn.getJSONObject(j),
			// ConnectionProperty.class));
			// System.out.println(property.getCfg().get(0).getHost());
			// }
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(0).getCfg().get(0).getData().get(0).getField());
			JSONObject jsonObject = JSONObject.fromObject(list.get(i));
			System.out.println(jsonObject);
		}
		// GroupProperty property=new GroupProperty();
		// property.setTime(5);
		// property.setGroup_name("asd");
		// ConnectionProperty property2=new ConnectionProperty();
		// property2.setUsername("123");
		// List<ConnectionProperty> list=new ArrayList<ConnectionProperty>();
		// list.add(property2);
		// property.setCfg(list);
		// JSONObject jsonObject = JSONObject.fromObject(property);
		// System.out.println(jsonObject);
	}

}
