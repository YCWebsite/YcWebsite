package com.yc.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.biz.AboutLogsBiz;

@Controller
public class LogController {
	
	private static final Log logger=LogFactory.getLog(ProjectsController.class);
	private AboutLogsBiz aboutLogsBiz;
	
	
	@Resource(name="aboutLogsBizImpl")
	public void setAboutLogsBiz(AboutLogsBiz aboutLogsBiz) {
		this.aboutLogsBiz = aboutLogsBiz;
	}
	
	@RequestMapping(value="/toShowLog.action")
	public String toShowLog(){
		logger.info("去显示日志页面...");
		return "backjsp/log";
	}
	
	// 日志查看
	@RequestMapping(value = "/showLog.action")
	@ResponseBody
	public void showLog(HttpServletRequest request,HttpServletResponse response) {
		logger.info("日志查看");
		String date = request.getParameter("da");
		String logsBasePath=aboutLogsBiz.showLogs(date);
		
		String ServerPath=request.getSession().getServletContext().getRealPath(  "/" );
		String str[]=ServerPath.split("\\\\");
		String path="";
		for(int i=0;i<str.length-1;i++){
			path+=str[i]+File.separator;
		}
		
		path+=logsBasePath;
		try {
			FileInputStream inputStream1 = new FileInputStream(path);
			 ServletOutputStream out=response.getOutputStream();
			   int b=0;
			   byte[] buffer=new byte[1024*1024*10];
			   while(b!=-1){
			 	  b=inputStream1.read(buffer);
			 	  out.write(buffer,0,buffer.length);
			   }
			   inputStream1.close();
			   out.close();
			   out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
