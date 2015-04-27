package util;

public class MyMath {

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static byte[] getbyte(int lengh, int flg, byte[] a) {
		byte[] b = new byte[lengh];
		for (int i = 0; i < lengh; i++) {
			b[i] = a[flg + i];
		}
		return b;
	}

	public static long bytes2long(byte[] b) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < b.length; i++) {
			res <<= 8;
			temp = b[i] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static int bytes2int32(byte[] bytes) {
		int num = bytes[3] & 0xFF;
		num |= ((bytes[2] << 8) & 0xFF00);
		num |= ((bytes[1] << 16) & 0xFF0000);
		num |= ((bytes[0] << 24) & 0xFF000000);
		return num;
	}

	public static int bytes2int16(byte[] bytes) {
		int num = bytes[1] & 0xFF;
		num |= ((bytes[0] << 8) & 0xFF00);
		return num;
	}

	public static short bytes2short(byte[] bytes) {
		return (short) (((bytes[1] << 8) | bytes[0] & 0xff));
	}

	public static float getFloat(byte[] b) {
		int accum = 0;
		accum = accum | (b[3] & 0xff) << 0;
		accum = accum | (b[2] & 0xff) << 8;
		accum = accum | (b[1] & 0xff) << 16;
		accum = accum | (b[0] & 0xff) << 24;
		return Float.intBitsToFloat(accum);
	}

	public static double byteToDouble(byte[] b) {
		long l;
		l = b[7];
		l &= 0xff;
		l |= ((long) b[6] << 8);
		l &= 0xffff;
		l |= ((long) b[5] << 16);
		l &= 0xffffff;
		l |= ((long) b[4] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[3] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[2] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[1] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[0] << 56);
		return Double.longBitsToDouble(l);
	}

	public static long byteToLong(byte[] b) {
		return ((((long) b[0] & 0xff) << 56) | (((long) b[1] & 0xff) << 48)
				| (((long) b[2] & 0xff) << 40) | (((long) b[3] & 0xff) << 32)
				| (((long) b[4] & 0xff) << 24) | (((long) b[5] & 0xff) << 16)
				| (((long) b[6] & 0xff) << 8) | (((long) b[7] & 0xff) << 0));
	}

	public static int bytes2int1(byte[] bytes) {
		int num = bytes[0] & 0xFF;
		return num;
	}
}
