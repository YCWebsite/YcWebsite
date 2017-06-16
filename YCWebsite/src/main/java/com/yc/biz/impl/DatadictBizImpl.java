package com.yc.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Datadict;
import com.yc.biz.DatadictBiz;
import com.yc.dao.BaseDao;

@Service
public class DatadictBizImpl implements DatadictBiz {

	private BaseDao baseDao;

	@Resource(name = "baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	//查询数据字典
	@Override
	public List<Datadict> selectDataByType(String type) {
		Datadict datadict=new Datadict();
		if(type!=null && !"".equals(type)){
			datadict.setD_type(type);
		}
		return this.baseDao.findAll(datadict, "selectDataByType");
	}

	//查询所有标题栏
	@Override
	public List<Datadict> selectMainMenu() {
		Datadict datadict=new Datadict();
		return this.baseDao.findAll(datadict, "selectMainMenu");
	}

	//修改数据字典
	@Override
	public boolean updateDataByType(Datadict datadict) {
		return this.baseDao.update(datadict, "updateDataByType")>0;
	}

}
