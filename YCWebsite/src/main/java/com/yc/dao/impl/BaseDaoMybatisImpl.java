package com.yc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yc.dao.BaseDao;

//操作sqlsession.find()  insert()  update()  delete()
@Repository  //ioc
public class BaseDaoMybatisImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{

	private String mapperLocation="com.yc.mapper.";
	
	private String getMapperId(T t,String sqlId){
		return mapperLocation+t.getClass().getSimpleName()+"Mapper."+sqlId;
	}
	
	@Override
	@Resource(name="sqlSessionFactory")  //di
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public List<T> findAll(T t, String sqlId) {
		//return super.getSqlSession().selectList("com.yc.mapper.PersonMapper.findAll");
		return super.getSqlSession().selectList(getMapperId(t, sqlId),t);
	}

	@Override
	public List<T> findAll(T t, Map map, String sqlId) {
		return super.getSqlSession().selectList(getMapperId(t, sqlId),map);
	}

	@Override
	public int update(T t, String sqlId) {
		return getSqlSession().update(getMapperId(t, sqlId),t);
	}

	@Override
	public int update(T t, Map map, String sqlId) {
		return getSqlSession().update(getMapperId(t, sqlId),map);
	}

	@Override
	public int updateMany(T t, List list, String sqlId) {
		return getSqlSession().update(getMapperId(t, sqlId),list);
	}

	@Override
	public int add(T t, String sqlId) {
		return getSqlSession().insert(getMapperId(t, sqlId),t);
	}

	@Override
	public int add(T t, Map map, String sqlId) {
		return getSqlSession().insert(getMapperId(t, sqlId),map);
	}

	@Override
	public int AddMany(T t, List list, String sqlId) {
		return getSqlSession().insert(getMapperId(t, sqlId),list);
	}

	@Override
	public int delete(T t, String sqlId) {
		return getSqlSession().delete(getMapperId(t, sqlId),t);
	}

	@Override
	public int delete(T t, Map map, String sqlId) {
		return getSqlSession().delete(getMapperId(t, sqlId),map);
	}

	@Override
	public int deleteMany(T t, List list, String sqlId) {
		return getSqlSession().delete(getMapperId(t, sqlId),list);
	}

	@Override
	public double findFunc(T t, String sqlId) {
		return super.getSqlSession().selectOne(getMapperId(t, sqlId));
	}

	@Override
	public double findFunc(T t, Map map, String sqlId) {
		return super.getSqlSession().selectOne(getMapperId(t, sqlId),map);
	}

}
