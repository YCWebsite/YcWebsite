package com.psl.sm.bean;

import java.io.Serializable;

public class Pay_vip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int vid;
	private float ch_money;
	private String ch_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public float getCh_money() {
		return ch_money;
	}
	public void setCh_money(float ch_money) {
		this.ch_money = ch_money;
	}
	public String getCh_date() {
		return ch_date;
	}
	public void setCh_date(String ch_date) {
		this.ch_date = ch_date;
	}
	@Override
	public String toString() {
		return "Pay_vip [id=" + id + ", vid=" + vid + ", ch_money=" + ch_money + ", ch_date=" + ch_date + "]";
	}
	
	
}
