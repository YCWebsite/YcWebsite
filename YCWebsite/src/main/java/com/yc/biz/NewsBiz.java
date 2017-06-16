package com.yc.biz;

import java.util.List;

import com.yc.bean.News;
import com.yc.bean.PaginationBean;

public interface NewsBiz {

	public boolean addNews(News news);
	
	public List<News> selectAllNews(Integer id);
	
	public List<News> selectCheckNews(String n_content);
	
	public boolean updateNewsById(News news);
	
	public boolean deleteNewsById(String s_id);
	
	//分页
	public PaginationBean<News> getPartJob(String size,String page);

}
