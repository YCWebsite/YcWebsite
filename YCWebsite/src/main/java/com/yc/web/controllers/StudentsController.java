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
import com.yc.bean.PaginationBean;
import com.yc.bean.Students;
import com.yc.biz.StudentsBiz;
import com.yc.web.utils.Message;

@Controller
public class StudentsController {
	private static final Log logger=LogFactory.getLog(StudentsController.class);
	
	private StudentsBiz studentsBiz;
	
	@Resource(name="studentsBizImpl")
	public void setStudentsbiz(StudentsBiz studentsBiz) {
		this.studentsBiz = studentsBiz;
	}

	@RequestMapping(value="/toStudents.action")
	public String toStudents(){
		logger.info("去students页面...");
		return "backjsp/students";
	}
	
	@RequestMapping(value="/show_students.action")
	public @ResponseBody void show_students(HttpServletRequest request,HttpServletResponse response,Students students) throws IOException{
		logger.info("去查看所有学生报名信息...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Students> paginationBean =this.studentsBiz.getPartJob(pageSize, currPage,students);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	@RequestMapping(value="/update_students.action")
	@ResponseBody
	public boolean update_students(Students students){
		logger.info("去修改学生报名信息...");
		return this.studentsBiz.updateStudentsById(students);
	}
	
	@RequestMapping(value="/search_students.action")
	public @ResponseBody void search_students(HttpServletResponse response,String choice) throws IOException{
		logger.info("根据报名状态查看学生报名信息...");
		List<Students> students=this.studentsBiz.selectAllStudents(choice);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(students));
	}
	
	@RequestMapping(value="/sendMessage.action")
	public @ResponseBody String sendMessage(Integer id) throws Exception{
		logger.info("发送短信...");
		List<Students> students=this.studentsBiz.selectStudentsById(id);
		Message message=new Message();
		message.sendMessage(students.get(0).getS_tel(), students.get(0).getS_name());
		Gson gson=new Gson();
		return gson.toJson("1");
	}
	
	//  ——————————————————————————————以下为前台页面显示—————————————————————————————————
	
	@RequestMapping(value="/add_students.action")
	public void add_students(HttpServletResponse response,Students students) throws IOException{
		logger.info("去添加学生报名信息...");
		response.setContentType("text/html; charset=UTF-8");
		if(this.studentsBiz.addStudents(students)){
			response.getWriter().println("<script>alert('报名成功！');location.href='index.action';</script>");
		}else{
			response.getWriter().println("<script>alert('报名失败！请重新输入');</script>");
		}
	}
	
}
