package com.yc.bean;

import java.io.Serializable;
import java.sql.Time;

public class Technology implements Serializable {

	private static final long serialVersionUID = 603893310276065924L;
	private Integer te_id;
	private String te_name;    //发表人
	private Time te_time;      //时间
	private String te_content;  //内容
	private Integer te_click;   //浏览次数
	private String te_title;    //标题
	
	public Integer getTe_id() {
		return te_id;
	}
	public void setTe_id(Integer te_id) {
		this.te_id = te_id;
	}
	public String getTe_name() {
		return te_name;
	}
	public void setTe_name(String te_name) {
		this.te_name = te_name;
	}
	public Time getTe_time() {
		return te_time;
	}
	public void setTe_time(Time te_time) {
		this.te_time = te_time;
	}
	public String getTe_content() {
		return te_content;
	}
	public void setTe_content(String te_content) {
		this.te_content = te_content;
	}
	public Integer getTe_click() {
		return te_click;
	}
	public void setTe_click(Integer te_click) {
		this.te_click = te_click;
	}
	public String getTe_title() {
		return te_title;
	}
	public void setTe_title(String te_title) {
		this.te_title = te_title;
	}
	
	
}
