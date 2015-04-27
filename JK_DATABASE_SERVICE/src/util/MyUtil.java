package util;

import java.util.ArrayList;
import java.util.List;

import vo.CountCFGimpl.DataBaseConnection;
import vo.CountCFGimpl.dependency.Feild;

public class MyUtil {

	public static String getString(List<String> list) {
		String re = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == 0)
				re += list.get(i);
			else
				re += "," + list.get(i);
		}
		return re;
	}

	public static String getDouble2String(List<Double> list) {
		String re = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == 0)
				re += String.valueOf(list.get(i));
			else
				re += "," + String.valueOf(list.get(i));
		}
		return re;
	}

	public static String getString(double[] list) {
		String re = "";
		for (int i = 0; i < list.length; i++) {
			if (i == 0)
				re += String.valueOf(list[i]);
			else
				re += "," + String.valueOf(list[i]);
		}
		return re;
	}

	// public static List<String> getAllFiledName(
	// DataBaseConnection dataBaseConnection) {
	// List<Feild> data = dataBaseConnection.getData();
	// List<String> re = new ArrayList<String>();
	// for (int i = 0; i < data.size(); i++) {
	// re.add(data.get(i).getField());
	// }
	// return re;
	// }

	public static List<String> getAllSourceFiledName(
			DataBaseConnection dataBaseConnection) {
		List<Feild> data = dataBaseConnection.getData();
		List<String> re = new ArrayList<String>();
		re.add(dataBaseConnection.getTimefiled());
		for (int i = 0; i < data.size(); i++) {
			re.add(data.get(i).getSource_field());
		}
		return re;
	}

	public static List<String> getAllPurposeFiledName(
			DataBaseConnection dataBaseConnection) {
		List<Feild> data = dataBaseConnection.getData();
		List<String> re = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			re.add(data.get(i).getPurpose_field());
		}
		return re;
	}

	public static int[] getAllFiledType(DataBaseConnection dataBaseConnection) {
		List<Feild> data = dataBaseConnection.getData();
		int[] re = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {
			re[i] = data.get(i).getType();
		}
		return re;
	}

	public static int getFieldTypeBySourceField(
			DataBaseConnection dataBaseConnection, String feildName) {
		List<Feild> data = dataBaseConnection.getData();
		int re = -1;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getSource_field().equals(feildName)) {
				return data.get(i).getType();
			}
		}
		return re;
	}

	public static int getFieldTypeByPurposeField(
			DataBaseConnection dataBaseConnection, String feildName) {
		List<Feild> data = dataBaseConnection.getData();
		int re = -1;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getPurpose_field().equals(feildName)) {
				return data.get(i).getType();
			}
		}
		return re;
	}

	public static int getFieldIntervalBySourceField(
			DataBaseConnection dataBaseConnection, String feildName) {
		List<Feild> data = dataBaseConnection.getData();
		int re = -1;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getSource_field().equals(feildName)) {
				return data.get(i).getInterval();
			}
		}
		return re;
	}

	public static int getFieldIntervalByPurposeField(
			DataBaseConnection dataBaseConnection, String feildName) {
		List<Feild> data = dataBaseConnection.getData();
		int re = -1;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getPurpose_field().equals(feildName)) {
				return data.get(i).getInterval();
			}
		}
		return re;
	}

	public static double getListEve(List<Double> list) {
		double re = 0;
		int flg = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != 0) {
				re += list.get(i);
				flg++;
			}
		}
		return re / flg;
	}

	public static double getListAdd(List<Double> list) {
		double re = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != 0) {
				re += list.get(i);
			}
		}
		return re;
	}

	public static double getListLast(List<Double> list) {
		return list.get(list.size() - 1);
	}

	public static void outTwoDimensionArray(double[][] array) {
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++)
				System.out.print("  " + array[i][j]);
			System.out.println();
		}
	}

	public static boolean doubleValidity(double... values) {
		boolean re = true;
		for (double value : values)
			re &= !(Double.isInfinite(value) || Double.isNaN(value));
		return re;
	}

	public static boolean doubleCorrectness(double... values) {
		boolean re = true;
		for (double value : values)
			re &= !(value == -51520188);
		return re;
	}
}
