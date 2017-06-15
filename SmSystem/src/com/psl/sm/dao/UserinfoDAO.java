package com.psl.sm.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.sm.bean.Userinfo;
import com.psl.sm.commons.DbHelper;


public class UserinfoDAO {

	DbHelper db=new DbHelper();

	//登录
	public Map<String, Object> login(Userinfo userinfo) throws SQLException, IOException{
		String sql="select id,name,pwd,email from userinfo where name=? and pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getName());
		params.add(userinfo.getPwd());	
		return db.findSingleObject(sql, params);
	}
	//根据用户名和邮箱查
	public Map<String, Object> findByEmail(Userinfo userinfo) throws SQLException, IOException{
		String sql="select id,name,pwd,email from userinfo where name=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getName());
		return db.findSingleObject(sql, params);
	}
	
	
	//修改密码
	public boolean updatePwd(Userinfo userinfo) throws FileNotFoundException, SQLException{
		String sql="update  userinfo set pwd=? where name=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getPwd());
		params.add(userinfo.getName());	
		return db.doUpdate(sql, params)>0;
	} 
	
	public boolean updateEmail(Userinfo userinfo) throws FileNotFoundException, SQLException{
		String sql="update  userinfo set email=? where name=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getEmail());
		params.add(userinfo.getName());	
		return db.doUpdate(sql, params)>0;
	} 
	
	//修改用户名
	public boolean updateUserName(Userinfo userinfo) throws FileNotFoundException, SQLException{
		String sql="update  userinfo set name=? where email=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getName());	
		params.add(userinfo.getEmail());	
		return db.doUpdate(sql, params)>0;
	}
	
	//找回密码
	public boolean findPwd(Userinfo userinfo) throws FileNotFoundException, SQLException{
		String sql="update  userinfo set pwd=? where email=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userinfo.getPwd());	
		params.add(userinfo.getEmail());
		return db.doUpdate(sql, params)>0;
	}
}
