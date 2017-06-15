package com.psl.sm.dao;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;

public class ExpendDAO {

	DbHelper db=new DbHelper();
	
	//查看报表
	public List<Map<String,Object>> createTable(String start,String end) throws FileNotFoundException{
		
		String sql="select  distinct sum(ex_money) money from expend "
				+ "where ex_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd')";
		List<Object> params=new ArrayList<Object>();
		params.add(start);
		params.add(end);
		return db.findMultiObject(sql, params);
	}
	
	//获取总支出
	public Map<String,Object> getMoney(String start,String end) throws FileNotFoundException{
		String sql="select  distinct sum(ex_money) money from expend "
				+ "where ex_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd')";
		List<Object> params=new ArrayList<Object>();
		params.add(start);
		params.add(end);
		return db.findSingleObject(sql, params);
	}
	
	//查看详情
	public List<Map<String,Object>> outputTable(String start,String end){
		List<Object> params=new ArrayList<Object>();
		params.add(start);
		params.add(end);
		String sql="select ex_name,ex_money,to_char(ex_date,'yyyy-MM-dd') ex_date,us.name uname from expend ex "
				+ "inner join userinfo us on us.id=ex.userid "
				+ "where ex_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd')"
				+ "order by ex_date desc";
		return db.findMultiObject(sql, params);
	}
}
