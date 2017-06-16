package com.yc.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface AboutSqlBiz {
	
	//数据库备份
	public boolean copySql(String filePath,String fileName);
	//数据库加载
	public boolean loadSql(String loadFilePath);
	
	//文件备份
	public boolean copyFile(String sourceFilePath, String targetFileName,String targetFilePath,String type);
	
	//文件加载
	public boolean recoverFile(String copyType,String serverPath,String time,List<MultipartFile> files,HttpServletRequest request);
}
