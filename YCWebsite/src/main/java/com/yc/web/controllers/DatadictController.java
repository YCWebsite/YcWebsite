package com.yc.web.controllers;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yc.bean.Datadict;
import com.yc.biz.DatadictBiz;

@Controller
public class DatadictController{
	private static final Log logger=LogFactory.getLog(DatadictController.class);
	
	private DatadictBiz datadictBiz;
	
	@Resource(name="datadictBizImpl")
	public void setnewsBiz(DatadictBiz datadictBiz) {
		this.datadictBiz = datadictBiz;
	}
	
	@RequestMapping(value="/toFootWords.action")
	public String toFootWords(String type,Model model){
		logger.info("去footWords页面...");
		List<Datadict> footWords=this.datadictBiz.selectDataByType(type);
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		return "backjsp/footWords";
	}
	
	@RequestMapping(value="/toAboutCompany.action")
	public String toAboutCompany(String type,Model model){
		logger.info("去aboutCompany页面...");
		List<Datadict> aboutCompany=this.datadictBiz.selectDataByType(type);
		model.addAttribute("aboutCompany", aboutCompany.get(0).getD_desc());
		return "backjsp/aboutCompany";
	}
	
	@RequestMapping(value="/toHistory.action")
	public String toHistory(String type,Model model){
		logger.info("去history页面...");
		List<Datadict> history=this.datadictBiz.selectDataByType(type);
		model.addAttribute("history", history.get(0).getD_desc());
		return "backjsp/history";
	}
	
	@RequestMapping(value="/updateDataByType.action")
	@ResponseBody
	public boolean updateDataByType(Datadict datadict){
		logger.info("去修改数据字典...");
		return this.datadictBiz.updateDataByType(datadict);
	}
	
}
