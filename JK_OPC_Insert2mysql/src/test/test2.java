package test;

import java.io.File;
import java.io.IOException;

public class test2 {

	public static void main(String[] args) {
		String redisLogFilePath = "./MTE1LjI4LjgwLjIyODMzMDZ0ZXN0XzJ0X3Rlc3Q=";
		File redisLogFile = new File(redisLogFilePath);
		try {
			redisLogFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
