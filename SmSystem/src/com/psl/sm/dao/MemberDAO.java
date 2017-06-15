package com.psl.sm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.sm.commons.DbHelper;


public class MemberDAO {

	DbHelper db=new DbHelper();
	
	//会员注册
	public   boolean addMember(List<Object> params) throws SQLException, IOException {
		String sql="insert into member values(seq_member_id.nextval,?,?,?,?,sysdate,?,default)";//to_date(sysdate,'yyyy-MM-dd')
		int i=db.doUpdate(sql,params);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	//会员信息全部查询
		public List<Map<String, Object>> findAllMember() throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  id,name,tel,money,reg_date from  member";					
			return db.findMultiObject(sql, null);
		}

		public boolean addVipMoney(String tel,float money,int userid)  throws SQLException, IOException {
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			params.add(money);
			params.add(userid);
			String sql="insert into pay_vip values(seq_pay_vip_id.nextval,(select id from member where tel = ?),?,sysdate,?)";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public List<Map<String, Object>> findMemberByName(String name) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  id,name,tel,money,reg_date from  member where name = ?";		
			List<Object> params=new ArrayList<Object>();
			params.add(name);
			return db.findMultiObject(sql, params);
		}

		public List<Map<String, Object>> findMemberByTel(String tel)  throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  id,name,tel,money,reg_date from  member where tel = ?";		
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			return db.findMultiObject(sql, params);
		}

		public List<Map<String, Object>> findMemberById(String id) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  name,tel from  member where id = ?";		
			List<Object> params=new ArrayList<Object>();
			params.add(id);
			return db.findMultiObject(sql, params);
		}

		public boolean updateMemberPwd(List<Object> params) throws SQLException, IOException{//管理员密码修改
			
			String sql="update member set pwd = ? where tel= ?";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public boolean payMemberMoney(String name, String tel, float ch_money,int userid) throws SQLException, IOException {
			List<Object> params=new ArrayList<Object>();
			params.add(name);
			params.add(tel);
			params.add(ch_money);
			params.add(userid);
			String sql="insert into pay_vip values(seq_pay_vip_id.nextval,(select id from member where name = ? and tel = ?),?,sysdate,?)";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public boolean updeteMemberMoney( float money,String name, String tel) throws SQLException, IOException{
			List<Object> params=new ArrayList<Object>();
			params.add(money);
			params.add(name);
			params.add(tel);
			String sql="update member set money = (money +?) where name =? and tel=?";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}


		public List<Map<String, Object>> findMemberRuleBuyByName(String name) throws SQLException, IOException {
			 DbHelper db=new DbHelper();
			String sql="select m.id id,m.name name,r.rname rname,r.mem_money mem_money,c.cnum cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date tc_date,us.name usname from userinfo us "+
							"inner join total_consume tc on us.id = tc.userid inner join consume c on tc.tc_id=c.tc_id  "+ 
							"inner join member m on tc.id = m.id inner join rule r on c.rid=r.rid  where m.name=? order by c.tc_id desc";		
			List<Object> params=new ArrayList<Object>();
			params.add(name);
			return db.findMultiObject(sql, params);
			
		}
		
		public List<Map<String, Object>> findMemberProductBuyByName(String name) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select m.id id,m.name name ,p.pname pname ,p.price price,cp.cs_num cs_num,(p.price*cp.cs_num)allmoney,tc.tc_date tc_date,us.name usname from userinfo us "+
							"inner join total_consume tc on us.id = tc.userid inner join consume_cp cp on tc.tc_id=cp.tc_id inner join member m on tc.id = m.id "+ 
							" inner join product p on cp.pid = p.pid   where m.name=? order by cp.tc_id desc";		
			List<Object> params=new ArrayList<Object>();
			params.add(name);
			return db.findMultiObject(sql, params);
		}
		
		public List<Map<String, Object>> findMemberRuleBuyByTel(String tel) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			
			String sql="select m.id id,m.name name,r.rname rname,r.mem_money mem_money,c.cnum cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date tc_date,us.name usname from userinfo us  "+
							"inner join total_consume tc on us.id = tc.userid inner join consume c on tc.tc_id=c.tc_id inner join member m on tc.id = m.id "+ 
							"inner join rule r on c.rid=r.rid  where m.tel=? order by c.tc_id desc";		
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			return db.findMultiObject(sql, params);
		}
		
		public List<Map<String, Object>> findMemberProductBuyByTel(String tel) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select m.id id,m.name name ,p.pname pname ,p.price price,cp.cs_num cs_num,(p.price*cp.cs_num)allmoney,tc.tc_date tc_date,us.name usname from userinfo us "+
							"inner join total_consume tc on us.id = tc.userid inner join consume_cp cp on tc.tc_id=cp.tc_id inner join member m on tc.id = m.id "+ 
							" inner join product p on cp.pid = p.pid   where m.tel=? order by cp.tc_id desc";		
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			return db.findMultiObject(sql, params);
		}


		public List<Map<String, Object>> findAllRule() throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  rname from  rule";					
			return db.findMultiObject(sql, null);
		}

		public List<Map<String, Object>> findAllProduct() throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  pname from  product";					
			return db.findMultiObject(sql, null);
		}

		public List<Map<String, Object>> memberLogin(List<Object> params) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  tel,money from  member where tel = ? and pwd = ?";	
			return db.findMultiObject(sql, params);
		}


		public List<Map<String, Object>> selectRnameNew(int rulenum,String rname) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  rid,rname,mem_money,(mem_money * ?)money from  rule where rname =?";					
			List<Object> params=new ArrayList<Object>();
			params.add(rulenum);
			params.add(rname);
			return db.findMultiObject(sql, params);
		}

		public List<Map<String, Object>> selectRnameNews(int rulenum,String rname) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  rid,rname,pub_money,(pub_money * ?)money from  rule where rname =?";					
			List<Object> params=new ArrayList<Object>();
			params.add(rulenum);
			params.add(rname);
			return db.findMultiObject(sql, params);
		}

		
		public List<Map<String, Object>> selectProductNew(int expensenum,String pname) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  pid,pname,price,(price * ?)money from  product where pname =?";					
			List<Object> params=new ArrayList<Object>();
			params.add(expensenum);
			params.add(pname);
			return db.findMultiObject(sql, params);
		}

		//添加消费总单
				public boolean addTotalConsume(String tel, int ys_money, int sh_money,int userid) throws SQLException, IOException {
					List<Object> params=new ArrayList<Object>();
					params.add(tel);
					params.add(ys_money);
					params.add(sh_money);
					params.add(userid);
					String sql="insert into total_consume values(seq_tc_id.nextval,(select id from member where tel = ?),sysdate,?,?,?)";
					int i=db.doUpdate(sql,params);
					if(i>0){
						return true;
					
					}else{
						return false;
					}
				}

		public boolean addConsume(int rid, int cnum)throws SQLException, IOException {
			List<Object> params=new ArrayList<Object>();
			params.add(rid);
			params.add(cnum);
			String sql="insert into consume values((select max(tc_id) from total_consume),?,?)";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public boolean addConsumeCP(int pid, int cs_num) throws SQLException, IOException {
			List<Object> params=new ArrayList<Object>();
			params.add(pid);
			params.add(cs_num);
			String sql="insert into consume_cp values(?,?,(select max(tc_id) from total_consume))";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		//扣除余额
		public boolean updateMemberMoney(int money,String tel)throws SQLException, IOException{
			List<Object> params=new ArrayList<Object>();
			params.add(money);
			params.add(tel);
			String sql="update member set money = (money - ?) where tel=?";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}

					
		public Map<String, Object> selectMemberMoney( String tel) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			String sql="select  money from  member where tel = ?";					
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			return db.findSingleObject(sql, params);
	
		}

		public boolean updateProductPnum(int cs_num,int pid) throws SQLException, IOException{
			List<Object> params=new ArrayList<Object>();
			params.add(cs_num);
			params.add(pid);
			String sql="update product set pnum = (pnum - ?) where pid=?";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}

		public boolean addTotalConsumes(int ys_money, int sh_money,int userid) throws SQLException, IOException {
			List<Object> params=new ArrayList<Object>();
			params.add(ys_money);
			params.add(sh_money);
			params.add(userid);
			String sql="insert into total_consume values(seq_tc_id.nextval,null,sysdate,?,?,?)";
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public boolean findMemberBuyByName(String name) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			List<Object> params=new ArrayList<Object>();
			params.add(name);
			String sql="select  * from  total_consume where id = (select id from MEMBER where name = ?)";	
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

		public boolean findMemberBuyByTel(String tel) throws SQLException, IOException {
			DbHelper db=new DbHelper();
			List<Object> params=new ArrayList<Object>();
			params.add(tel);
			String sql="select  * from  total_consume where id = (select id from MEMBER where tel = ?)";	
			int i=db.doUpdate(sql,params);
			if(i>0){
				return true;
			
			}else{
				return false;
			}
		}

}
