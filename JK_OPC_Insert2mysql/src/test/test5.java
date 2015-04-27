package test;

import java.io.File;
import java.io.IOException;

public class test5 {

	public static void main(String[] args) throws IOException {
		File file = new File("./abc");
		if (!file.exists()) {
			file.createNewFile();
		}
		else if (file.exists()) {
			file.renameTo(new File("./123"));
			file.delete();
		}
	}

}
