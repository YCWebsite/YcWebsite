package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.News;
import com.yc.bean.PaginationBean;
import com.yc.biz.NewsBiz;
import com.yc.dao.BaseDao;

@Service
public class NewsBizImpl implements NewsBiz {

	private BaseDao baseDao;

	@Resource(name = "baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 添加新闻
	@Override
	public boolean addNews(News news) {
		return this.baseDao.add(news, "addNews") > 0;
	}

	// 查询所有新闻/根据id
	@Override
	public List<News> selectAllNews(Integer id) {
		News news = new News();
		if (id != null && !"".equals(id)) {
			news.setN_id(id);
		}
		return this.baseDao.findAll(news, "selectAllNews");
	}

	// 修改新闻(根据id)
	@Override
	public boolean updateNewsById(News news) {
		return this.baseDao.update(news, "updateNewsById") > 0;
	}

	// 删除新闻(可多选)
	@Override
	public boolean deleteNewsById(String id) {
		List<News> newsList = new ArrayList<News>();
		News news = null;
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			news = new News();
			news.setN_id(Integer.parseInt(ids[i]));
			newsList.add(news);
		}
		return this.baseDao.deleteMany(news, newsList, "deleteNewsById") > 0;
	}

	// 分页
	@Override
	public PaginationBean<News> getPartJob(String size, String page) {
		int currPage = 1;// 默认当前页为第一页
		int pageSize = 10;// 默认页面显示的页数为10条
		if (size != null) {
			pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		int totalSize=getTotalSize();
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
		News news = new News();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<News> list = this.baseDao.findAll(news, map, "findByPages");
		return new PaginationBean<News>(currPage, (int) totalPage, list, totalSize);
	}

	// 总页数
	private int getTotalPage(int pageSize) {
		News news = new News();
		news.setPageSize(pageSize);
		return (int) this.baseDao.findAll(news, "getTotalPage").get(0);
	}

	// 获取总条数
	private int getTotalSize() {
		News news = new News();
		return (int) this.baseDao.findAll(news, "getTotalSize").get(0);
	}

	//模糊查询
	@Override
	public List<News> selectCheckNews(String n_content) {
		News news = new News();
		if (n_content != null && !"".equals(n_content)) {
			news.setN_content("%"+n_content+"%");
		}
		return this.baseDao.findAll(news, "selectCheckNews");
	}
}
