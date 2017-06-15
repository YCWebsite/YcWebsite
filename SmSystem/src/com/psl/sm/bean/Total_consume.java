package com.psl.sm.bean;

import java.io.Serializable;

public class Total_consume implements Serializable{

	private static final long serialVersionUID = 1L;
	private int tc_id;
	private Integer id;  //当id=0时，为非会员收费
	private String tc_date;
	private  float ys_money;			
	private  float 	sh_money;
	
	public int getTc_id() {
		return tc_id;
	}
	public void setTc_id(int tc_id) {
		this.tc_id = tc_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTc_date() {
		return tc_date;
	}
	public void setTc_date(String tc_date) {
		this.tc_date = tc_date;
	}
	public float getYs_money() {
		return ys_money;
	}
	public void setYs_money(float ys_money) {
		this.ys_money = ys_money;
	}
	public float getSh_money() {
		return sh_money;
	}
	public void setSh_money(float sh_money) {
		this.sh_money = sh_money;
	}
	@Override
	public String toString() {
		return "Total_consume [tc_id=" + tc_id + ", id=" + id + ", tc_date=" + tc_date + ", ys_money=" + ys_money
				+ ", sh_money=" + sh_money + "]";
	}
}
