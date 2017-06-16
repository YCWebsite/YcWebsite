package com.yc.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.SysUsers;
import com.yc.biz.SysUsersBiz;
import com.yc.dao.BaseDao;

@Service
public class SysUsersBizImpl implements SysUsersBiz {

	private BaseDao<SysUsers> baseDao;
	
	@Resource(name="baseDaoMybatisImpl")
	public void setBaseDao(BaseDao<SysUsers> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<SysUsers> login(SysUsers sysUsers) {
		return this.baseDao.findAll(sysUsers, "login");
	}

}
