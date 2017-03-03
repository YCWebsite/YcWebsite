package com.yc.dao;

import java.util.List;
import java.util.Map;

//这是dao层的接口
public interface BaseDao<T> {

	public List<T> findAll(T t,String sqlId);
	public List<T> findAll(T t,Map map,String sqlId);
	
	public void update(T t,String sqlId);
	public void update(T t,Map map,String sqlId);
	//update xxx set sex=x where id in(1,2,3)
	public void updateMany(T t,List list,String sqlId);
	
	public void add(T t,String sqlId);
	public void add(T t,Map map,String sqlId);
	public void AddMany(T t,List list,String sqlId);
	
	public void delete(T t,String sqlId);
	public void delete(T t,Map map,String sqlId);
	//delete from xxx where id in(1,2,3)
	public void deleteMany(T t,List list,String sqlId);
	
	//聚合函数
	public double findFunc(T t,String sqlId);  //select count(*) from xxx
	public double findFunc(T t,Map map,String sqlId);
}
