package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Activities implements Serializable {

	private static final long serialVersionUID = 8279832372916477571L;
	private Integer ac_id;
	private Date ac_time; // 活动时间
	private String ac_illus; // 说明
	private String ac_pic; // 图片（多图）
	private List<MultipartFile> picUrl; // 对应界面上的<input name="picUrl" type="file">

	public List<MultipartFile> getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(List<MultipartFile> picUrl) {
		this.picUrl = picUrl;
	}
	
	// 标准的javabean方法，在页面上利用${activities.picsStringList}
	public List<String> getPicsStringList() {
		List<String> list = new ArrayList<String>();
		if (ac_pic != null && ac_pic.length() > 0) {
			String[] strs = ac_pic.split(",");
			for (String s : strs) {
				list.add(s);
			}
		}
		return list;
	}

	// 分页
	private Integer pageSize;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getAc_id() {
		return ac_id;
	}

	public void setAc_id(Integer ac_id) {
		this.ac_id = ac_id;
	}

	public Date getAc_time() {
		return ac_time;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setAc_time(Date ac_time) {
		this.ac_time = ac_time;
	}

	public String getAc_illus() {
		return ac_illus;
	}

	public void setAc_illus(String ac_illus) {
		this.ac_illus = ac_illus;
	}

	public String getAc_pic() {
		return ac_pic;
	}

	public void setAc_pic(String ac_pic) {
		this.ac_pic = ac_pic;
	}

}
