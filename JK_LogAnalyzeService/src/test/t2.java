package test;

import bowl.PointBowl;
import service.ApplicationRegisterUtil;

public class t2 {
	public static void main(String[] args) {
		ApplicationRegisterUtil.redistAllfactory();
		ApplicationRegisterUtil.redistAllPoint();
		
		int i=PointBowl.getInstance().select("ycyl_cq_gfjf_gfjzt1").getFactory_id();
		System.out.println(i);
	}
}
