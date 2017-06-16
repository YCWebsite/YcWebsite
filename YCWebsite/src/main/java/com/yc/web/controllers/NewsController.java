package com.yc.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yc.bean.News;
import com.yc.bean.PaginationBean;
import com.yc.biz.NewsBiz;

@Controller
public class NewsController {

	private static final Log logger=LogFactory.getLog(NewsController.class);
	
	private NewsBiz newsBiz;
	
	@Resource(name="newsBizImpl")
	public void setnewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}

	@RequestMapping(value="/toNews.action")
	public String toNews(){
		logger.info("去news页面...");
		return "backjsp/news";
	}
	
	@RequestMapping(value="/show_news.action")
	public @ResponseBody void show_news(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有新闻内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<News> paginationBean =this.newsBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	@RequestMapping(value="/add_news.action")
	@ResponseBody
	public boolean add_news(News news,HttpServletResponse response) throws IOException{
		logger.info("去添加新闻...");
		return this.newsBiz.addNews(news);
	}
	
	/*@RequestMapping(value="/toUpdateNews.action")
	public String toUpdateNews(Integer n_id,Model model){
		logger.info("去updateNews页面...");
		List<News> news=this.newsBiz.selectAllNews(n_id);
		model.addAttribute("news", news.get(0));
		return "backjsp/updateNews";
	}*/
	
	@RequestMapping(value="/update_news.action")
	@ResponseBody
	public boolean update_news(News news,String n_id,String n_title,String n_reportor,String n_sort,String n_content){
		logger.info("去修改新闻...");
		return this.newsBiz.updateNewsById(news);
	}
	
	@RequestMapping(value="/delete_news.action")
	@ResponseBody
	public boolean delete_news(String n_id){
		logger.info("去删除项目...");
		return this.newsBiz.deleteNewsById(n_id);
	}
	
	@RequestMapping(value="/search_news.action")
	@ResponseBody
	public void search_news(HttpServletResponse response,String value) throws IOException{
		logger.info("去查找新闻...");
		List<News> news=this.newsBiz.selectCheckNews(value);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(news));
	}
	
	//  ——————————————————————————————以下为前台页面显示—————————————————————————————————
	
	@RequestMapping(value="/show_news2.action")
	public @ResponseBody void show_news2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有新闻内容...");
		List<News> news=this.newsBiz.selectAllNews(null);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(news));
	}
}
