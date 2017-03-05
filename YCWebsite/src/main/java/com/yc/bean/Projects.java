package com.yc.bean;

import java.io.Serializable;
import java.sql.Time;

public class Projects implements Serializable {

	private static final long serialVersionUID = -1819646038260294843L;
	private Integer p_id;
	private String p_name;     //项目名
	private String p_pic;      //项目图片
	private String p_developer;	 //开发者
	private Time p_time;		//开发时间
	private String p_addr;     //项目发布地址 
	
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
	public Time getP_time() {
		return p_time;
	}
	public void setP_time(Time p_time) {
		this.p_time = p_time;
	}
	public String getP_addr() {
		return p_addr;
	}
	public void setP_addr(String p_addr) {
		this.p_addr = p_addr;
	}
	
}
