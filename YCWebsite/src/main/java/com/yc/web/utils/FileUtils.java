package com.yc.web.utils;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

public class FileUtils {

		//生成文件夹的名称
		public static String getNowDateStr() {
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int day = now.get(Calendar.DATE);
			return File.separator+year + File.separator + month + File.separator + day+File.separator;
		}
		
		//生成文件名
		public  static String getUniqueFileName() {
			String str = UUID.randomUUID().toString();
			return str.replace("-", "");
		}
}
