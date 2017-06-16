package com.yc.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetFilePath {

	private String filePath="";
	public String getFilePath(){
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileServerName.properties");
			Properties p = new Properties();
			p.load(inputStream);
			
			filePath = p.getProperty("picRootName");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
