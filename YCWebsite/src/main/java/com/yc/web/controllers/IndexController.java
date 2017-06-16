package com.yc.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.bean.Activities;
import com.yc.bean.Courses;
import com.yc.bean.Datadict;
import com.yc.bean.Employ;
import com.yc.bean.JobDetails;
import com.yc.bean.News;
import com.yc.bean.Projects;
import com.yc.bean.SysUsers;
import com.yc.bean.Teachers;
import com.yc.biz.ActivitiesBiz;
import com.yc.biz.CoursesBiz;
import com.yc.biz.DatadictBiz;
import com.yc.biz.EmployBiz;
import com.yc.biz.JobDetailsBiz;
import com.yc.biz.NewsBiz;
import com.yc.biz.ProjectsBiz;
import com.yc.biz.SysUsersBiz;
import com.yc.biz.TeachersBiz;
import com.yc.web.utils.GetIp;

@Controller
public class IndexController {
	private static final Log logger=LogFactory.getLog(IndexController.class);
	
	@Autowired
	private DatadictBiz datadictBiz;
	@Autowired
	private ProjectsBiz projectsBiz;
	@Autowired
	private ActivitiesBiz activitiesBiz;
	@Autowired
	private NewsBiz newsBiz;
	@Autowired
	private TeachersBiz teachersBiz;
	@Autowired
	private CoursesBiz coursesBiz;
	@Autowired
	private JobDetailsBiz jobDetailsBiz;
	@Autowired
	private SysUsersBiz sysUsersBiz;
	@Autowired
	private EmployBiz employBiz;

	@RequestMapping(value = "/toBackMain.action")
	public String toBackMain() {
		return "backjsp/backMain";
	}

	@RequestMapping(value = "/index.action")
	public String toIndex(Model model) {
		// 去显示关于公司
		List<Datadict> aboutCompany = this.datadictBiz.selectDataByType("关于公司");
		model.addAttribute("aboutCompany", aboutCompany.get(0).getD_desc());
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		// 查看所有学员项目
		List<Projects> projects = this.projectsBiz.selectAllProjects(null);
		model.addAttribute("projects", projects);
		//去查看所有学员就业情况
		List<JobDetails> jobDetailsList =jobDetailsBiz.selectJobDetails();
		model.addAttribute("jobDetails", jobDetailsList);
		// 去显示前台活动图片
		List<Activities> activities = this.activitiesBiz.selectAllActivities(null);
		String str = "";
		for (int i = 0; i < activities.size(); i++) {
			String[] pics = activities.get(i).getAc_pic().split(",");
			// System.out.println(pics.length);
			for (int j = 0; j < pics.length; j++) {
				str += pics[j] + ",";
			}
		}
		model.addAttribute("pic", str);
		List<Teachers> listTeacher = this.teachersBiz.findTeachers(null);
		List<Teachers> listTeam = this.teachersBiz.findTeam(null);
		List<Courses> listCourses = this.coursesBiz.findCourses(null);
		model.addAttribute("teachers", listTeacher);
		model.addAttribute("team", listTeam);
		model.addAttribute("courses", listCourses);
		return "index";
	}

	@RequestMapping(value = "/studentProject.action")
	public String toStudentProject(Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		// 查看所有学员项目
		List<Projects> projects = this.projectsBiz.selectAllProjects(null);
		model.addAttribute("projects", projects);
		return "studentProject";
	}

	@RequestMapping(value = "/about.action")
	public String toAbout(Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		// 查看关于源辰的信息
		List<Datadict> aboutCompany = this.datadictBiz.selectDataByType("关于公司");
		model.addAttribute("aboutCompany", aboutCompany.get(0).getD_desc());
		// 去查看所有新闻内容
		List<News> news = this.newsBiz.selectAllNews(null);
		model.addAttribute("news", news);
		return "about";
	}

	@RequestMapping(value = "/teacher.action")
	public String toTeachers( Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		// 去查看所有新闻内容
		List<News> news = this.newsBiz.selectAllNews(null);
		model.addAttribute("news", news);
		//去查看所有师资信息
		List<Teachers> list = this.teachersBiz.findTeachers(null);
		model.addAttribute("teachers", list);
		//去显示所有招聘信息
		List<Employ> employList=this.employBiz.selectAllEmploy(null);
		model.addAttribute("employList", employList);
		return "teacher";
	}

	@RequestMapping(value = "/subject.action")
	public String toCourses(Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		//去查看所有课程体系
		List<Courses> list = this.coursesBiz.findCourses(null);
		model.addAttribute("courses", list);
		return "subject";
	}
	
	@RequestMapping(value = "/findWork.action")
	public String toFindWork( Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		//去查看所有学员就业情况
		List<JobDetails> jobDetailsList =jobDetailsBiz.selectJobDetails();
		model.addAttribute("jobDetails", jobDetailsList);
		return "findWork";
	}
	
	@RequestMapping(value = "/company.action")
	public String toCompany(Model model) {
		// 去查看所有主标题
		List<Datadict> datadict = this.datadictBiz.selectMainMenu();
		model.addAttribute("titles", datadict);
		// 去显示页脚文字
		List<Datadict> footWords = this.datadictBiz.selectDataByType("页脚文字");
		model.addAttribute("footWords", footWords.get(0).getD_desc());
		//去显示公司历史
		List<Datadict> history=this.datadictBiz.selectDataByType("公司历史");
		model.addAttribute("history", history.get(0).getD_desc());
		return "company";
	}

	//to登录界面
	@RequestMapping(value="/toLogin.action")
	public String toLogin(){
		return "backjsp/login";
	}
	
	//登录
	@RequestMapping(value="/login.action")
	public String login(SysUsers sysUsers,@RequestParam String validateCode,HttpSession session,HttpServletRequest request) throws IOException{
		List<SysUsers> user=this.sysUsersBiz.login(sysUsers);
		String randCode=(String) session.getAttribute("rand");
		if(!validateCode.equals(randCode)){
			session.setAttribute("errmsg", "验证码错误");
			return "backjsp/login";
		}
		if(user!=null && user.size()>0){
			logger.info( new GetIp().getRemortIP(request));
			return "backjsp/backMain";
		}
		return "backjsp/login";
	}
}
