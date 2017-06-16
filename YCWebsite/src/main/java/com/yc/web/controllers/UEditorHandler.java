package com.yc.web.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.yc.bean.UploadData;
/**
 * 富文本编辑器核心处理类
 * @author dengshuangjun
 * Created by dengshuangjun on 2016/11/20.
 */
@Controller
@RequestMapping("/ued")
public class UEditorHandler {
	//http://localhost:8080/Limits/ued/config?action=config&&noCache=1481515802619
	/**
	 * 编辑器数据配置
	 * @param request
	 * @return
	 */
	 @RequestMapping(value="/config.action",params="action=config")
	 @ResponseBody
	 public String config(HttpServletRequest request) {
		 String rootPath = request.getSession().getServletContext().getRealPath("/");   
		 String exec = new ActionEnter(request, rootPath).exec();
	     return exec; 
	 }
	/**
	 * 上传图片
	 * @param upfile
	 * @return
	 */
	@RequestMapping(value="/config.action",params="action=uploadimage")
	@ResponseBody
	public UploadData upImageFile(MultipartFile upfile){
		 if(upfile!=null){
			String paths=System.getProperty("YCWebsite.webapp");//获取项目在服务器中的绝对路径，我的图片是存在服务器的webapp的pics目录下面，这个需要一个配置
			paths=paths.substring(0,paths.lastIndexOf(File.separator));
			String realPath = paths.substring(0,paths.lastIndexOf(File.separator))+ File.separator+"uploadImages";
			String picName =".."+File.separator+"uploadImages"+File.separator+picSting()+new Date().getTime()+
					upfile.getOriginalFilename().substring(upfile.getOriginalFilename().indexOf("."));
			//图片名字的生成，getOriginalFilename获取上传图片的名字然后截取后缀
			try {
				FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, picName));
				//FIXME :下面的代码是为了备份到新浪云挂载的共享存储，因为项目的新发布会覆盖tomcat下面的文件
				FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, ".."+File.separator+".."+File.separator+picName));
				
				String original = upfile.getOriginalFilename();//原名字
				UploadData imageData=new UploadData(original.substring(0,original.indexOf(".")),original.substring(0,original.indexOf(".")),picName,upfile.getSize(),original.substring(original.indexOf(".")+1),"SUCCESS");
				return imageData;
			} catch (IOException e) {
				return null;
			}
		}
		return new UploadData(null, null, null, 0, null, "上传失败");
	 }
	 
	 /**
	 * 生成含有字母的验证码
	 * @return
	 */
	 public String picSting(){
		Random ra = new Random();
		int num1;
		StringBuffer sbf2 = new StringBuffer();
		while (sbf2.length() < 8) {
			if(ra.nextInt(3)==0){
				num1=ra.nextInt(10);
				sbf2.append(num1);
			}else if(ra.nextInt(3)==1){
				num1=ra.nextInt(26)+97;
				sbf2.append((char)num1);
			}else{
				num1=ra.nextInt(26)+65;
				sbf2.append((char)num1);
			}
		}
		return sbf2.toString();
	}
	/**
	 * 上传视频
	 * @param upfile
	 * @return
	 */
	/* @RequestMapping(value="/config.do",params="action=uploadvideo")
	 @ResponseBody
	 public UploadData uploadvideo(MultipartFile upfile){
		 if(upfile!=null){
			 String paths=System.getProperty("Limits.webapp");//获取项目在服务器中的绝对路径，我的图片是存在服务器的webapp的pics目录下面，这个需要一个配置
					paths=paths.substring(0,paths.lastIndexOf(File.separator));
					String realPath = paths.substring(0,paths.lastIndexOf(File.separator))+File.separator+WeixinbasicKey.UPLOADPIC;
				String picName =".."+File.separator+WeixinbasicKey.UPLOADPIC+File.separator+picSting()+new Date().getTime()+
						upfile.getOriginalFilename().substring(upfile.getOriginalFilename().indexOf("."));
				//图片名字的生成，getOriginalFilename获取上传图片的名字然后截取后缀
				try {
					FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, picName));
					//FIXME :下面的代码是为了备份到新浪云挂载的共享存储，因为项目的新发布会覆盖tomcat下面的文件
					FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, ".."+File.separator+".."+File.separator+picName));
					
					String original = upfile.getOriginalFilename();//原名字
					UploadData videoData=new UploadData(original.substring(0,original.indexOf(".")),picName,picName,upfile.getSize(),original.substring(original.indexOf(".")+1),"SUCCESS");
					return videoData;
				} catch (IOException e) {
					return null;
				}
			}
			return null;
	 }
	 /**
	  * 上传文件
	  * @param upfile
	  * @return
	  */
	/* @RequestMapping(value="/config.do",params="action=uploadfile")
	 @ResponseBody
	 public UploadData uploadFile(MultipartFile upfile){
		 if(upfile!=null){
			 String paths=System.getProperty("Limits.webapp");//获取项目在服务器中的绝对路径，我的图片是存在服务器的webapp的pics目录下面，这个需要一个配置
					paths=paths.substring(0,paths.lastIndexOf(File.separator));
					String realPath = paths.substring(0,paths.lastIndexOf(File.separator))+ File.separator+WeixinbasicKey.UPLOADPIC;
				String picName =".."+File.separator+WeixinbasicKey.UPLOADPIC+File.separator+picSting()+new Date().getTime()+
						upfile.getOriginalFilename().substring(upfile.getOriginalFilename().indexOf("."));
				//图片名字的生成，getOriginalFilename获取上传图片的名字然后截取后缀
				try {
					FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, picName));
					//FIXME :下面的代码是为了备份到新浪云挂载的共享存储，因为项目的新发布会覆盖tomcat下面的文件
					FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, ".."+File.separator+".."+File.separator+picName));
					
					String original = upfile.getOriginalFilename();//原名字
					UploadData videoData=new UploadData(original.substring(0,original.indexOf(".")),upfile.getName(),picName,upfile.getSize(),original.substring(original.indexOf(".")+1),"SUCCESS");
					return videoData;
				} catch (IOException e) {
					return null;
				}
			}
			return null;
	 }*/
}
