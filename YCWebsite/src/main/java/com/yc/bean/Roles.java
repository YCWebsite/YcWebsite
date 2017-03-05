package com.yc.bean;

import java.io.Serializable;

public class Roles implements Serializable {

	private static final long serialVersionUID = -8996112184813782475L;
	private Integer r_id;
	private String r_name;
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	
	
}
