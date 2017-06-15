package com.psl.sm.utils;

public class TimeUtils {
	
	//判断闰年，返回boolean型
	public static boolean isRun(int year){
		if((year%4==0&&year%100!=0)  ||(year%400==0)){
			return true;		
		}
		return false;
	}

	
}
