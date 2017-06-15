package com.psl.sm.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;

public class Pay_vipDAO {
	
	DbHelper db=new DbHelper();
	
	//查看报表
		public List<Map<String,Object>> payCreateTable(String start,String end) throws FileNotFoundException{
			String sql="select distinct sum(ch_money)money from PAY_VIP "
					+ "where ch_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd') ";
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			return db.findMultiObject(sql, params);
		}
		
		//查看详情
		public List<Map<String,Object>> outputTable(String start,String end){
			List<Object> params=new ArrayList<Object>();
			params.add(start);
			params.add(end);
			String sql="select p.id pid,p.vid,ch_money,to_char(p.ch_date,'yyyy-MM-dd') ch_date,p.userId,u.name uname,m.tel mtel,m.name mname from PAY_VIP p "+
						"left join member m on p.vid=m.id left join userinfo u on p.userId=u.id "+
						"where p.ch_date between to_date(?,'yyyy-MM-dd')  and to_date(?,'yyyy-MM-dd') "+
						"order by p.ch_date desc";
			return db.findMultiObject(sql, params);
		}
		
}
