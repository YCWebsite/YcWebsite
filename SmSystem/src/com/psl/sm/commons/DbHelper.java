package com.psl.sm.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {

	static{
		try {
			Class.forName(Myproperties.getInstance().getProperty("driverClass"));	//加载驱动
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取数据库连接对象
	public Connection getConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Myproperties.getInstance().getProperty("url"),Myproperties.getInstance());//获取连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//关闭连接对象
	public void closeAll1(ResultSet rs,Statement stmt,Connection conn){
		
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//关闭连接对象
	public void closeAll2(ResultSet rs,PreparedStatement pstmt,Connection conn){
			if(null!=rs){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=pstmt){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=conn){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	/**
	 * 设置参数的方法
	 * @param pstmt 预连接对象
	 * @param params 对象的集合
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * 
	 */	
	private void setParams(PreparedStatement pstmt,List<Object> params) throws SQLException, FileNotFoundException{
		if(null!=params && params.size()>0){
			for(int i=0;i<params.size();i++){
				if(params.get(i) instanceof File){	//判断参数是否为文件对象
					File file = (File)params.get(i);//强转为文件对象
					InputStream in = new FileInputStream(file);//转为输入流对象
					pstmt.setBinaryStream(i+1, in,(int)file.length());
				}else{
					pstmt.setString(i+1, params.get(i).toString()); //params�д洢ֵ��˳����?һ��
				}
			}
		}
	}
	
	/**
	 * 修改照片
	 * @param sql
	 * @param id
	 * @param file
	 * @return
	 * @throws FileNotFoundException 
	 */
	public int updateImg(String sql,int id,File file) throws FileNotFoundException{
		FileInputStream in = new FileInputStream(file);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, in, (int)file.length());
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally{
			this.closeAll2(null, pstmt, conn);
		}
		return result;
	}
	
	/**
	 * 增   删  改   单条sql语句
	 * @param sql
	 * @param params 
	 * @return
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */	
	public int doUpdate(String sql,List<Object> params){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt,params);   //设置参数
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			this.closeAll2(null, pstmt, conn);
		}
		return result;
	}
	
	/**
	 * 批量处理    多条sql语句的执行    多条sql语句的结果要么一起成功，要么一起失败
	 * 手动设置事务提交
	 * @param sqls
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int doUpdate(List<String> sqls,List<List<Object>> params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = this.getConn();
			conn.setAutoCommit(false);//关闭隐式事务，即执行sql语句后需要手动提交
			if(null!=sqls && sqls.size()>0){
				//循环sql语句
				for(int i=0;i<sqls.size();i++){
					String sql = sqls.get(i);
					pstmt = conn.prepareStatement(sql);
					//给当前sql语句设置
					if(params!=null){
						this.setParams(pstmt, params.get(i));
					}
					result = pstmt.executeUpdate();	
				}
			}
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//回滚事务
			conn.rollback();
			throw e;
		}  finally{
			//恢复现场
			conn.setAutoCommit(true);
			this.closeAll2(null, pstmt, conn);
		}
		return result;
	}
	
	/**
	 * 查询多条记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws FileNotFoundException 
	 */
	public List<Map<String,Object>> findMultiObject(String sql,List<Object> params){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Object> map = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs); //获取结果集中的所有列表
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn: columnNames){ 	//循环列名 将列表作用Map的键  根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll2(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 *查询单条记录  select * from 表名   where id = 1
	 * @param sql
	 * @param params
	 * @return
	 * @throws FileNotFoundException 
	 */
	public Map<String,Object> findSingleObject(String sql,List<Object> params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Object> map = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs); 
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn: columnNames){	
					map.put(cn, rs.getObject(cn));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll2(rs, pstmt, conn);
		}
		return map;
	}
	
	/**
	 * 根据结果集对象获取所有的列名  存在一个list集合中    jdbc2.0ȡԪ����
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private List<String> getAllColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		if(null!=rs){
			for(int i=0;i<rs.getMetaData().getColumnCount();i++){
				columnNames.add(rs.getMetaData().getColumnName(i+1));
			}
		}
		return columnNames;
	}
	
	
}
