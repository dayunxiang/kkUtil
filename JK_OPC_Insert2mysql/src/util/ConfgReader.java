package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import vo.ConnectionProperty;
import vo.DataProperty;
import vo.GroupProperty;

public class ConfgReader {
	private String path;

	public ConfgReader(String path) {
		this.path = path;
	}

	public List<GroupProperty> getGroupPropertyList() {
		List<GroupProperty> re = new ArrayList<GroupProperty>();
		String encoding = "GBK";
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("cfg", ConnectionProperty.class);
		classMap.put("data", DataProperty.class);
		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
					JSONObject object1 = JSONObject.fromObject(lineTxt);
					GroupProperty property=(GroupProperty) JSONObject.toBean(object1,GroupProperty.class, classMap);
					re.add(property);
//					re += lineTxt;
				}
				read.close();
			} else {
				throw new RuntimeException("读取文件失败");
			}
		} catch (Exception e) {
			System.out.println("读取配置文件失败");
			e.printStackTrace();
		}
		return re;
	}

	public String readTxtFile() {
		String re = "";
		String encoding = "GBK";
		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					re += lineTxt;
				}
				read.close();
			} else {
				throw new RuntimeException("δ�ҵ���Ҫ��ȡ���ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return re;
	}
}
