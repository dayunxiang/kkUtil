package main;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import util.MyMath;
import bean.Data;

public class SetValue {
	private byte[] dataFrame;

	public SetValue(String value) {
		this.dataFrame = MyMath.hexStringToBytes(value);
	}

	public SetValue(byte[] value) {
		this.dataFrame = value;
	}
	
	public List<Data> getDatas() {
		List<Data> re=new ArrayList<Data>();
		int flg = 0;
		byte[] jkatb = MyMath.getbyte(4, flg, dataFrame);
		String jkat = null;
			try {
				jkat = new String(jkatb, "GB2312");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!jkat.equals("jkat"))
			throw new RuntimeException("非法的数据帧");
		flg += 4;

		// 帧长度，四个字节
		byte[] lenth = MyMath.getbyte(4, flg, dataFrame);
		// System.out.println("十六进制：" +lenth);
		long i = MyMath.bytes2long(lenth);
		if (i != dataFrame.length)
			throw new RuntimeException("数据帧长度错误，存在数据丢失");
		flg += 4;

		// 时间戳，四个字节
		byte[] timebyte = MyMath.getbyte(4, flg, dataFrame);
		long tt = MyMath.bytes2long(timebyte);
		tt *= 1000;
		Date date = new Date(tt);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("时间：" + format.format(date));
		flg += 4;

		// 数据参数区，有几个数据，四个字节
		byte[] noOfvalue = MyMath.getbyte(4, flg, dataFrame);
		long no = MyMath.bytes2long(noOfvalue);
		flg += 4;

		for (int j = 0; j < no; j++) {
			Data data=new Data();
			data.setDate(date);
			byte[] namelong = MyMath.getbyte(4, flg, dataFrame);
			int nameLong = MyMath.bytes2int32(namelong);
//			 System.out.println("ID长度：" + nameLong);
			flg += 4;

			byte[] namebyte = MyMath.getbyte(nameLong, flg, dataFrame);
//			 System.out.println("ID：" + name);
			String name = null;
				try {
					name = new String(namebyte, "GB2312");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			data.setName(name);
//			System.out.println(name);
			flg += nameLong;

			byte[] valuelong = MyMath.getbyte(1, flg, dataFrame);
			int valueLong = MyMath.bytes2int1(valuelong);
//			 System.out.println("参数类型：" + valueLong);
			flg += 1;
			// 0表示16位整型short，
			// 1表示32位整型，
			// 2表示64位整型,
			// 3表示4字节float,
			// 4表示8字节double,
			// 5表示1字节整形(用来表示bool值)
			byte[] oneofvalue;
			switch (valueLong) {
			case 0: {
				oneofvalue = MyMath.getbyte(2, flg, dataFrame);
				Short Value = MyMath.bytes2short(oneofvalue);
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 2;
				break;
			}
			case 1: {
				oneofvalue = MyMath.getbyte(4, flg, dataFrame);
				Integer Value = MyMath.bytes2int32(oneofvalue);
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 4;
				break;
			}
			case 2: {
//				throw new RuntimeException("64位整形尚未完成");
//				System.out.println("参数的类型：64位整型");
				oneofvalue = MyMath.getbyte(8, flg, dataFrame);
				Long Value = MyMath.byteToLong(oneofvalue);
//				System.out.println("参数：" + Value);
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 8;
				break;
			}
			case 3: {
				oneofvalue = MyMath.getbyte(4, flg, dataFrame);
				Float Value = MyMath.getFloat(oneofvalue);
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 4;
				break;
			}
			case 4: {
				oneofvalue = MyMath.getbyte(8, flg, dataFrame);
				Double Value = MyMath.byteToDouble(oneofvalue);
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 8;
				break;
			}
			case 5: {
				oneofvalue = MyMath.getbyte(1, flg, dataFrame);
//				System.out.println(oneofvalue[0]);
				Boolean Value = oneofvalue[0] == 1;
				data.setValuetype(Value.getClass().getName());
				data.setValue(Value);
				flg += 1;
				break;
			}
			default: {
				throw new RuntimeException("未知的数据类型");
			}
			}
			re.add(data);
		}
		return re;
	}
}
