package com.yc.bean;

import java.io.Serializable;
import java.security.Timestamp;

public class News implements Serializable {

	
	private static final long serialVersionUID = -1850670895436268588L;
	private Integer n_id;
	private String n_title;    //标题
	private String n_content;   //内容
	private Integer n_click;    //点击次数
	private Timestamp n_time;	//创建时间
	private Integer n_sort;     //排序
	private String n_reportor;  //发布者 
	
	public Integer getN_id() {
		return n_id;
	}
	public void setN_id(Integer n_id) {
		this.n_id = n_id;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public Integer getN_click() {
		return n_click;
	}
	public void setN_click(Integer n_click) {
		this.n_click = n_click;
	}
	public Timestamp getN_time() {
		return n_time;
	}
	public void setN_time(Timestamp n_time) {
		this.n_time = n_time;
	}
	public Integer getN_sort() {
		return n_sort;
	}
	public void setN_sort(Integer n_sort) {
		this.n_sort = n_sort;
	}
	public String getN_reportor() {
		return n_reportor;
	}
	public void setN_reportor(String n_reportor) {
		this.n_reportor = n_reportor;
	}
	
}
