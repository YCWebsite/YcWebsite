package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Courses implements Serializable {

	private static final long serialVersionUID = -7346238853185557362L;
	private Integer cs_id;
	private String cs_name;     //方向名
	private String cs_namePic;  //方向说明图片
	private String cs_pic;      //图片说明
	private String cs_version;  //版本
	private String cs_text;     //文字说明
	
	//分页的每页大小
	private Integer pageSize;
	
	
	private MultipartFile cs_nameURL;//对应界面name的url地址
	private MultipartFile cs_picURL;//对应界面的url地址
		
	//在标准的javabean中，可以利用${pdfsStringList}获取
	public String getCspicURLStringList(){
		if(cs_pic!=null&&cs_pic.length()>0){
			return cs_pic;
		}
		return null;
	}
	
	//在标准的javabean中，可以利用${pdfsStringList}获取
		public String getCsnameURLStringList(){
			if(cs_namePic!=null&&cs_namePic.length()>0){
				return cs_namePic;
			}
			return null;
		}
	
	
	
	
	
	public MultipartFile getCs_nameURL() {
		return cs_nameURL;
	}

	public void setCs_nameURL(MultipartFile cs_nameURL) {
		this.cs_nameURL = cs_nameURL;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public MultipartFile getCs_picURL() {
		return cs_picURL;
	}

	public void setCs_picURL(MultipartFile cs_picURL) {
		this.cs_picURL = cs_picURL;
	}

	public Integer getCs_id() {
		return cs_id;
	}
	public void setCs_id(Integer cs_id) {
		this.cs_id = cs_id;
	}
	
	public String getCs_namePic() {
		return cs_namePic;
	}

	public void setCs_namePic(String cs_namePic) {
		this.cs_namePic = cs_namePic;
	}

	public String getCs_name() {
		return cs_name;
	}
	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}
	public String getCs_pic() {
		return cs_pic;
	}
	public void setCs_pic(String cs_pic) {
		this.cs_pic = cs_pic;
	}
	public String getCs_version() {
		return cs_version;
	}
	public void setCs_version(String cs_version) {
		this.cs_version = cs_version;
	}
	public String getCs_text() {
		return cs_text;
	}
	public void setCs_text(String cs_text) {
		this.cs_text = cs_text;
	}

	@Override
	public String toString() {
		return "Courses [cs_id=" + cs_id + ", cs_name=" + cs_name + ", cs_namePic=" + cs_namePic + ", cs_pic=" + cs_pic
				+ ", cs_version=" + cs_version + ", cs_text=" + cs_text + ", pageSize=" + pageSize + ", cs_nameURL="
				+ cs_nameURL + ", cs_picURL=" + cs_picURL + "]";
	}

	
	
}
