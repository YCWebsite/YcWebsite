package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Employ;
import com.yc.bean.PaginationBean;
import com.yc.biz.EmployBiz;
import com.yc.dao.BaseDao;
@Service
public class EmployBizImpl implements EmployBiz {

	private BaseDao baseDao;

	@Resource(name="baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Employ> selectAllEmploy(Integer id) {
		Employ employ=new Employ();
		if(id!=null && !"".equals(id)){
			employ.setE_id(id);
		}
		return  this.baseDao.findAll(employ, "findEmploy");
	}

	@Override
	public boolean addEmploy(Employ employ) {
		return baseDao.add(employ, "addEmploy")>0;
	}

	@Override
	public boolean updateEmployById(Employ employ) {
		return baseDao.update(employ, "updateEmployById")>0;
	}

	@Override
	public boolean deleteEmployById(String id) {
		List<Employ> employList =new ArrayList<Employ>();
		Employ employ=null;
		String [] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			employ=new Employ();
			employ.setE_id(Integer.parseInt(ids[i]));
			employList.add(employ);
		}
		return baseDao.deleteMany(employ, employList, "deleteEmployById")>0;
	}

	// 分页
		@Override
		public PaginationBean<Employ> getPartJob(String size, String page) {
				
				int currPage = 1;// 默认当前页为第一页
				int pageSize = 10;// 默认页面显示的页数为10条
				if (size != null) {
					pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
				}
				int totalPage = getTotalPage(pageSize);
				int totaleSize=getTotalSize();
				if (page != null) {
					currPage = Integer.parseInt(page); // 取到当前页面
					if (currPage > totalPage) {// 表示最后一页
						currPage = (int) totalPage;
					} else if (currPage < 1) {// 第一页
						currPage = 1;
					}
				}
				int start = (currPage - 1) * pageSize;
				int end = pageSize;
				Employ employ = new Employ();
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("start", start);
				map.put("end", end);
				List<Employ> list = this.baseDao.findAll(employ, map, "findByPages");
				return new PaginationBean<Employ>(currPage, (int) totalPage, list, totaleSize);
			}

		//总页数
		private int getTotalPage(int pageSize) {
			Employ employ = new Employ();
			employ.setPageSize(pageSize);
			return  (int) this.baseDao.findAll(employ, "getTotalPage").get(0);
		}
		
		//总条数
		private int getTotalSize() {
			Employ employ = new Employ();
			return (int) this.baseDao.findAll(employ, "getTotalSize").get(0);
		}

		//根据时间修改状态
		@Override
		public boolean updateEmployByTime(Employ employ) {
			List<Employ> employList=this.baseDao.findAll(employ, "findEmployByTime");
			for(int i=0;i<employList.size();i++){
				employList.get(i).getE_id();
			}
			
			return false;
		}

	
}
