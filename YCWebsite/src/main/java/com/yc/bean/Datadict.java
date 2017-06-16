package com.yc.bean;

import java.io.Serializable;

public class Datadict implements Serializable {
	private static final long serialVersionUID = -356669904549714340L;
	private String d_id;
	private String d_type;
	private String d_desc;
	
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public String getD_desc() {
		return d_desc;
	}
	public void setD_desc(String d_desc) {
		this.d_desc = d_desc;
	}
	@Override
	public String toString() {
		return "Datadict [d_id=" + d_id + ", d_type=" + d_type + ", d_desc="
				+ d_desc + "]";
	}
	public Datadict(String d_id, String d_type, String d_desc) {
		super();
		this.d_id = d_id;
		this.d_type = d_type;
		this.d_desc = d_desc;
	}
	public Datadict() {
		super();
	}
	
}
