package com.yc.web.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.biz.AboutLogsBiz;
import com.yc.biz.AboutSqlBiz;

@Controller
public class AboutSqlController {

	private static final Log logger = LogFactory.getLog(AboutSqlController.class);
	private AboutSqlBiz aboutSqlBiz;
	

	@Resource(name="aboutSqlBizImpl")
	public void setAboutSqlBiz(AboutSqlBiz aboutSqlBiz) {
		this.aboutSqlBiz = aboutSqlBiz;
	}

	// toShowCopySql.jsp页面toShowCopySql.action
	@RequestMapping(value = "/toShowCopySqls.action")
	public String toShowCopySql() {
		return "backjsp/copySql";
	}
	
	
	@RequestMapping(value = "/toCopyFile.action")
	public String toCopyFile() {
		return "backjsp/copyFile";
	}
	
	@RequestMapping(value = "/toRecoverFile.action")
	public String toRecoverFile() {
		return "backjsp/recoverFile";
	}

	@RequestMapping(value = "/toRecoverSql.action")
	public String toRecoverSql() {
		return "backjsp/loadSql";
	}
	
	//数据库备份
	@RequestMapping(value = "/copySQL.action")
	@ResponseBody
	public String copySql(@RequestParam("path") String filePath,@RequestParam("name") String fileName) {
		logger.info("数据库备份");
		boolean flag=aboutSqlBiz.copySql(filePath,fileName);
		if(flag){
			return "success";
		}
		return "error";
	}

	// 数据库还原
	@RequestMapping(value = "/loadSql.action")
	public String toOriginaSql() {
		String filepath = "d:\\test.sql"; // 备份的路径地址
		// 新建数据库test
		String stmt1 = "mysqladmin -u root -proot create test";
		String stmt2 = "mysql -u root -proot test < " + filepath;
		String[] cmd = { "cmd", "/c", stmt2 };

		try {
			Runtime.getRuntime().exec(stmt1);
			Runtime.getRuntime().exec(cmd);
			System.out.println("数据已从 " + filepath + " 导入到数据库中");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//文件备份
	@RequestMapping(value="/copyFile.action")
	@ResponseBody
	public boolean copyFile(@RequestParam("targetFilePath") String targetFilePath,
			@RequestParam("targetFileName") String targetFileName,
			@RequestParam("options") String type,HttpServletRequest request){
		logger.info("文件备份");
		//D:\Tomcat\apache-tomcat-7.0.30\webapps\YCWebsite\
		String ServerPath=request.getSession().getServletContext().getRealPath(  "/" );
		String str[]=ServerPath.split("\\\\");
		String path="";
		for(int i=0;i<str.length-1;i++){
			path+=str[i]+File.separator;
		}
		return aboutSqlBiz.copyFile(path,targetFileName, targetFilePath,type);
	}
	
	//文件还原
	@RequestMapping(value = "/recoverFile.action")
	@ResponseBody
	public boolean recoverFile(@RequestParam("type") String type,
			@RequestParam("path") List<MultipartFile> files,@RequestParam("date") String time,
			HttpServletRequest request) {
		String ServerPath=request.getSession().getServletContext().getRealPath(  "/" );
		String path=ServerPath.substring(0, 32);
		boolean flag=aboutSqlBiz.recoverFile(type,path,time,files,request);
		return flag;
	}
}
