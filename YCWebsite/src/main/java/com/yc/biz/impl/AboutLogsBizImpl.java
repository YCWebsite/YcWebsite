package com.yc.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.yc.biz.AboutLogsBiz;

@Service
public class AboutLogsBizImpl implements AboutLogsBiz {

	@Override
	public String showLogs(String date) {
		// 如果是查看当天的操作日志
		String logsBasePath="";
		try {
			Date nowTime = new Date(System.currentTimeMillis());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(nowTime);
			//获取配置信息
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
			Properties p = new Properties();
			p.load(inputStream);
			String logPath=p.getProperty("log4j.appender.file.File");
			
			if (time.equals(date)) {
				logsBasePath = logPath.substring(25);
			} else {
				logsBasePath = logPath.substring(25)+"."+date;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return logsBasePath;
	}

}
