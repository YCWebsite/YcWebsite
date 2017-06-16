package com.yc.bean;

import java.io.Serializable;
import java.util.Date;

public class Students implements Serializable {

	private static final long serialVersionUID = -126555970239925228L;
	private Integer s_id;
	private String s_name; // 姓名
	private String s_tel; // 电话
	private String s_direction; // 意向方向
	private Date s_time; // 报名时间
	private String s_status; // 状态

	// 分页
	private Integer pageSize;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getS_time() {
		return s_time;
	}

	public void setS_time(Date s_time) {
		this.s_time = s_time;
	}

	public String getS_status() {
		return s_status;
	}

	public void setS_status(String s_status) {
		this.s_status = s_status;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_tel() {
		return s_tel;
	}

	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}

	public String getS_direction() {
		return s_direction;
	}

	public void setS_direction(String s_direction) {
		this.s_direction = s_direction;
	}

	@Override
	public String toString() {
		return "Students [s_id=" + s_id + ", s_name=" + s_name + ", s_tel=" + s_tel + ", s_direction=" + s_direction
				+ "]";
	}

}
