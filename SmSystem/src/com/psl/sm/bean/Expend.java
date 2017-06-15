package com.psl.sm.bean;

import java.io.Serializable;

public class Expend implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer ex_id;
	private String ex_name;
	private float ex_money;
	private String ex_date;
	
	
	public Integer getEx_id() {
		return ex_id;
	}
	public void setEx_id(Integer ex_id) {
		this.ex_id = ex_id;
	}
	public String getEx_name() {
		return ex_name;
	}
	public void setEx_name(String ex_name) {
		this.ex_name = ex_name;
	}
	public float getEx_money() {
		return ex_money;
	}
	public void setEx_money(float ex_money) {
		this.ex_money = ex_money;
	}
	public String getEx_date() {
		return ex_date;
	}
	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}
	@Override
	public String toString() {
		return "Expend [ex_id=" + ex_id + ", ex_name=" + ex_name + ", ex_money=" + ex_money + ", ex_date=" + ex_date
				+ "]";
	}
	
	
}
