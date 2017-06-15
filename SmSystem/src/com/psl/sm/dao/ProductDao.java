package com.psl.sm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;

/**
 * 套餐dao
 * 
 * @author Randow
 *
 */

public class ProductDao {

	private DbHelper db = new DbHelper();

	public List<Map<String, Object>> searchProduct() {
		String sql = "select * from product order by pid";
		List<Map<String, Object>> list = db.findMultiObject(sql, null);
		return list;
	}

	public int modifyProduct(List<Object> param) {
		String sql = "update product set pname=?,ptype=?,price=? where pid=?";
		return db.doUpdate(sql, param);
	}

	public Map<String, Object> findProductByPname(List<Object> param) {
		String sql = "select * from product where pname =? order by pid";
		return db.findSingleObject(sql, param);
	}

	public int addProductNum(Integer addNum, String addPname) {
		String sql = "update product set pnum = (select p.pnum+? from product p where p.pname=?) where pname=?";
		List<Object> param = new ArrayList<Object>();
		param.add(addNum);
		param.add(addPname);
		param.add(addPname);
		return db.doUpdate(sql, param);
	}

	public int addNewProduct(String addPname, Integer addNum, Float price, String jishu,int userid) {
		String sql = "insert into product values(seq_product_id.nextval,?,?,?,?,?)";
		List<Object> param = new ArrayList<Object>();
		param.add(addPname);
		param.add(addNum);
		param.add(jishu);
		param.add(price);
		param.add(userid);
		return db.doUpdate(sql, param);
	}

	public int addExpend(String note, Float addMoney,int userid) {
		String sql = "insert into expend values(seq_expend_id.nextval,?,?,sysdate,?)";
		List<Object> param = new ArrayList<Object>();
		param.add(note);
		param.add(addMoney);
		param.add(userid);
		return db.doUpdate(sql, param);
	}

	public int addExpend(List<Object> param) {
		String sql = "insert into expend values(seq_expend_id.nextval,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
		return db.doUpdate(sql, param);
	}


}
