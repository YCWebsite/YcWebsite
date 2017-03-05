package com.yc.bean;

import java.io.Serializable;
import java.sql.Time;

public class history implements Serializable {

	private static final long serialVersionUID = 3585418809341774140L;
	private Integer h_id;
	private Time h_createtime;
	private String h_content;
	private String h_creater;
	private Number h_reg;
	public Integer getH_id() {
		return h_id;
	}
	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}
	public Time getH_createtime() {
		return h_createtime;
	}
	public void setH_createtime(Time h_createtime) {
		this.h_createtime = h_createtime;
	}
	public String getH_content() {
		return h_content;
	}
	public void setH_content(String h_content) {
		this.h_content = h_content;
	}
	public String getH_creater() {
		return h_creater;
	}
	public void setH_creater(String h_creater) {
		this.h_creater = h_creater;
	}
	public Number getH_reg() {
		return h_reg;
	}
	public void setH_reg(Number h_reg) {
		this.h_reg = h_reg;
	}
	
}
