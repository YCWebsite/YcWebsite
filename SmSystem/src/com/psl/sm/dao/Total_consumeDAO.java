package com.psl.sm.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;

public class Total_consumeDAO {

	DbHelper db=new DbHelper();
	
		//查看年报表
		public List<Map<String,Object>> createTable(String start,String end) throws FileNotFoundException{
			String sql="select distinct sum(ys_money) money "
					+ "from total_consume "
			+ "where tc_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd')";
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			return db.findMultiObject(sql, params);
		}
		
		//获取总收入
		public Map<String,Object> getMoney(String start,String end) throws FileNotFoundException{
			String sql="select distinct sum(ys_money) money "
					+ "from total_consume "
			+ "where tc_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd')";
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			return db.findSingleObject(sql, params);
		}
		
		//查看产品详情
		public  List<Map<String,Object>> findProduct(String start,String end){
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			String sql="select pname,cs_num ,price,(pnum * price)money,to_char(tc_date,'yyyy-MM-dd') tc_date ,us.name uname "
					+ "from userinfo us inner join total_consume tc on us.id = tc.userid inner join consume_cp cc "
					+ "on cc.tc_id = tc.tc_id inner join product p on cc.pid = p.pid "
					+ "where tc.id is null and  tc_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd') "
					+ "order by tc_date desc";
			return db.findMultiObject(sql, params);
		}
		
		public  List<Map<String,Object>> findRule(String start,String end){
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			String sql="select to_char(tc_date,'yyyy-MM-dd') tc_date,rname,cnum,pub_money,(cnum * pub_money)money,us.name uname from userinfo us "
					+ "inner join total_consume tc on us.id = tc.userid "
					+ "inner join consume c on c.tc_id = tc.tc_id "
					+ "inner join  rule r on r.rid=c.rid where tc.id is null and "
					+ "tc_date between to_date(?,'yyyy-MM-dd')  "
					+ "and to_date(?,'yyyy-MM-dd')";
			return db.findMultiObject(sql, params);
		}
		
}
