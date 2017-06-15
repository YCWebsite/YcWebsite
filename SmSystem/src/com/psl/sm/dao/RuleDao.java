package com.psl.sm.dao;


import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;

/**
 * 套餐dao 
 * @author Randow
 *
 */

public class RuleDao {
	
	private DbHelper db = new DbHelper();
	
	public List<Map<String, Object>> searchRule(){
		String sql="select * from rule order by rid";
		List<Map<String, Object>> list=db.findMultiObject(sql, null);
		return list;
	}

	public int modifyRule(List<Object> param) {
		String sql="update rule set rname = ?,rnote = ?,mem_money = ?,pub_money = ? where rid = ?";
		return db.doUpdate(sql, param);
	}

	public int addRule(List<Object> param) {
		String sql="insert into rule values(seq_rule_rid.nextval,?,?,?,?,?)";
		return db.doUpdate(sql, param);
	}

}
