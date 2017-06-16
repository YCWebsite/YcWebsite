package com.yc.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Projects implements Serializable {

	private static final long serialVersionUID = -1819646038260294843L;
	private Integer p_id;
	private String p_name; // 项目名
	private String p_developer; // 开发者
	private Date p_time; // 开发时间
	private String p_addr; // 项目发布地址
	private String p_pic; // 项目图片
	private List<MultipartFile> picUrl; // 对应界面上的<input name="picUrl"
										// type="file">

	public List<MultipartFile> getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(List<MultipartFile> picUrl) {
		this.picUrl = picUrl;
	}

	// 标准的javabean方法，在页面上利用${projects.picsStringList}
	public String getPicsStringList() {
		if (p_pic != null && p_pic.length() > 0) {
			return p_pic;
		}
		return null;
	}

	// 分页
	private Integer pageSize;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_pic() {
		return p_pic;
	}

	public void setP_pic(String p_pic) {
		this.p_pic = p_pic;
	}

	public String getP_developer() {
		return p_developer;
	}

	public void setP_developer(String p_developer) {
		this.p_developer = p_developer;
	}

	public Date getP_time() {
		return p_time;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setP_time(Date p_time) {
		this.p_time = p_time;
	}

	public String getP_addr() {
		return p_addr;
	}

	public void setP_addr(String p_addr) {
		this.p_addr = p_addr;
	}

	@Override
	public String toString() {
		return "Projects [p_id=" + p_id + ", p_name=" + p_name + ", p_pic=" + p_pic + ", p_developer=" + p_developer
				+ ", p_time=" + p_time + ", p_addr=" + p_addr + "]";
	}
}
