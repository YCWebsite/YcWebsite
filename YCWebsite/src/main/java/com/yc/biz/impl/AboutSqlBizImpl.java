package com.yc.biz.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yc.biz.AboutSqlBiz;
import com.yc.web.utils.GetFilePath;

@Service
public class AboutSqlBizImpl implements AboutSqlBiz {

	private String useName;
	private String pwd;
	private String database;
	//private String hostName;
	private String filepath; // 数据库备份的地址
	
	//数据库备份
	@Override
	public boolean copySql(String filePath,String fileName) {
		// 数据库导出
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			useName = p.getProperty("jdbc.username");
			pwd = p.getProperty("jdbc.password");
			database = p.getProperty("jdbc.url").substring(p.getProperty("jdbc.url").lastIndexOf("/") + 1);
			/*hostName = p.getProperty("jdbc.url").substring(p.getProperty("jdbc.url").lastIndexOf("//") + 1,
					p.getProperty("jdbc.url").lastIndexOf("//") + 11);*/

			filepath = filePath+File.separator+fileName;
			String stmt = "cmd /C F:\\mysqldump "+database+" -u"+useName+" -p"+pwd+" > "+filepath;
			
			Process process = Runtime.getRuntime().exec(stmt);
			Scanner in = new Scanner(process.getInputStream());
			while(in.hasNextLine()){
				System.out.println(in.nextLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	//数据库恢复
	@Override
	public boolean loadSql(String loadFilePath) {
		return false;
	}
	//文件备份
	@Override
	public boolean copyFile(String sourceFilePath, String targetFileName,String targetFilePath,String type) {
		try {
			if(type.equals("logs")){
				//获取日志文件服务器位置
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
				Properties p = new Properties();
				p.load(inputStream);
				String logPath=p.getProperty("log4j.appender.file.File");
				//logs/log.log
				String log=logPath.substring(25);
				String logName=log.substring(0, log.lastIndexOf("/"));
				//源文件的路径	E:\apache-tomcat-7.0.30\webapps\logs
				sourceFilePath =sourceFilePath+logName;
			}
			if(type.equals("images")){
				//获取图片文件的服务器位置
				GetFilePath filePath=new GetFilePath();
				sourceFilePath=sourceFilePath+filePath.getFilePath();
			}
				
			String url=targetFilePath+File.separator+targetFileName+File.separator;
			//当获取到这个文件路径时，截取最后一个分隔符后的文件名，并生成文件名
			(new File(url)).mkdirs(); // 创建目标文件夹
			File[] file = (new File(sourceFilePath)).listFiles(); // 获取源文件夹当前下的文件或目录
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()){ // 复制文件
					//String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);
					copyFile(file[i], new File(url + file[i].getName()));
				}
				if (file[i].isDirectory()){ // 复制目录
					String sourceDir = sourceFilePath + File.separator + file[i].getName();
					String targetDir = url + File.separator + file[i].getName();
					copyDirectiory(sourceDir, targetDir);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//复制文件
	private void copyFile(File sourceFile, File targetFile){
		 BufferedInputStream inBuff = null;
	    BufferedOutputStream outBuff = null;
	    try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile)); 
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] b = new byte[1024 * 5]; 
			int len;
			while ((len = inBuff.read(b)) != -1) {
			    outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("未找到文件，文件备份失败");  
		} catch (IOException e) {
			throw new RuntimeException("文件备份失败");  
		} finally {
	        try {
				if (inBuff != null)
				    inBuff.close();
				if (outBuff != null)
				    outBuff.close();
			} catch (IOException e) {
				throw new RuntimeException("文件备份失败");  
			}
	    }
	}
	
	//复制路径
	private void copyDirectiory(String sourceDir, String targetDir) {
		(new File(targetDir)).mkdirs();
	    //获取所有的文件
	    File[] file = (new File(sourceDir)).listFiles();
	    for (int i = 0; i < file.length; i++) {
	        if (file[i].isFile()) {
	            File sourceFile = file[i];
	            File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
	            copyFile(sourceFile, targetFile);//递归调用
	        }
	        if (file[i].isDirectory()){
	            String dir1 = sourceDir + File.separator + file[i].getName();
	            String dir2 = targetDir + File.separator + file[i].getName();
	            copyDirectiory(dir1, dir2);//递归调用
	        }
	    }
	}

	private boolean flag;
	/*//文件还原
	@Override
	public boolean recoverFile(String copyType,String serverPath,String time,List<MultipartFile> files,HttpServletRequest request) {
		Map<String, String> map=new HashMap<>();
		String FilePath="";
		try {
			if(copyType.equals("logs")){
				//获取日志文件服务器位置
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
				Properties p = new Properties();
				p.load(inputStream);
				
				String logPath=p.getProperty("log4j.appender.file.File");
				//logs/log.log
				String log=logPath.substring(25);
				
				String logName=log.substring(0, log.lastIndexOf("/"));	//logs
				FilePath=serverPath+logName;		// E:\apache-tomcat-7.0.30\webapps\logs
				
				map=refreshFileList(FilePath);
				
			}
			if(copyType.equals("images")){
				//获取图片文件的服务器位置
				GetFilePath filePath=new GetFilePath();
				// E:\\apache-tomcat-7.0.30\\webapps\\uploadImages\\2017\\03\\23
				FilePath=serverPath+filePath.getFilePath()+File.separator+time.substring(0,4)
					+File.separator+time.substring(5, 7).replace("0", "")+File.separator+time.substring(8, 10);
				//获取文件服务器下的所有文件名
				map=refreshFileList(FilePath);
			}
			
			for(int j=0;j<files.size();j++){
				String originalName=files.get(j).getOriginalFilename();
				for(Entry<String, String> entry : map.entrySet()){
					String key=entry.getKey();
					flag=key.equals(originalName);
					if(!flag){
						if(  files.get(j).isEmpty() ){
							continue;
						}
						String newfilename = originalName;
						String saveDir=FilePath;
						String newFilePath=saveDir+File.separator+newfilename;
						File saveDirFile=new File( saveDir);
						if (!saveDirFile.exists()) {
							saveDirFile.mkdirs();
						}
						File imageFile = new File(newFilePath);
						if(copyType.equals("images")){
							files.get(j).transferTo(imageFile);
						}else if(copyType.equals("logs")){
							//以流的形式写入
							
							
							
							
						}
						flag=true;
					}else{//如果要插入的图片在服务器中存在，则跳出循环
						flag=false;
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}*/
	
	//文件还原
		@Override
		public boolean recoverFile(String copyType,String serverPath,String time,List<MultipartFile> files,HttpServletRequest request) {
			Map<String, String> map=new HashMap<>();
			String FilePath="";
			try {
				if(copyType.equals("logs")){
					//获取日志文件服务器位置
					InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
					Properties p = new Properties();
					p.load(inputStream);
					
					String logPath=p.getProperty("log4j.appender.file.File");
					//logs/log.log
					String log=logPath.substring(25);
					
					String logName=log.substring(0, log.lastIndexOf("/"));	//logs
					FilePath=serverPath+logName;		// E:\apache-tomcat-7.0.30\webapps\logs
					
					map=refreshFileList(FilePath);
					
				}
				if(copyType.equals("images")){
					//获取图片文件的服务器位置
					GetFilePath filePath=new GetFilePath();
					// E:\\apache-tomcat-7.0.30\\webapps\\uploadImages\\2017\\03\\23
					FilePath=serverPath+filePath.getFilePath()+File.separator+time.substring(0,4)
						+File.separator+time.substring(5, 7).replace("0", "")+File.separator+time.substring(8, 10);
					//获取文件服务器下的所有文件名
					map=refreshFileList(FilePath);
				}
				
				for(int j=0;j<files.size();j++){
					String originalName=files.get(j).getOriginalFilename();
					for(Entry<String, String> entry : map.entrySet()){
						String key=entry.getKey();
						flag=key.equals(originalName);
						if(!flag){
							if(  files.get(j).isEmpty() ){
								continue;
							}
							String newfilename = originalName;
							String saveDir=FilePath;
							String newFilePath=saveDir+File.separator+newfilename;
							File saveDirFile=new File( saveDir);
							if (!saveDirFile.exists()) {
								saveDirFile.mkdirs();
							}
							File imageFile = new File(newFilePath);
							if(copyType.equals("images")){
								files.get(j).transferTo(imageFile);
							}else if(copyType.equals("logs")){
								//以流的形式写入
								InputStream in=files.get(j).getInputStream();
								OutputStream out=new FileOutputStream(imageFile);
								byte []bt=files.get(j).getBytes();
								while(in.read(bt)!=-1){
									out.write(bt);
								}
							}
							flag=true;
						}else{//如果要插入的图片在服务器中存在，则跳出循环
							flag=false;
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return flag;
		}
	
	public Map<String, String> refreshFileList(String strPath) {
		Map<String, String> map=new HashMap<>();
		File file = new File(strPath);
	    if (!file.isDirectory()) {
	    	map.put(file.getName(), file.getName()) ;
	    } 
	    else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(strPath + File.separator + filelist[i]);
                if (!readfile.isDirectory()) {
                	map.put(readfile.getName(), readfile.getName());
                } else if (readfile.isDirectory()) {
                	refreshFileList(strPath + File.separator  + filelist[i]) ;
                }
            }
	    }
	    return map;
	}

}
