package com.yc.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

	@RequestMapping(value="/toBackMain")
	public String toStudentListBook( HttpSession session,Model model){
		return "backjsp/backMain";
	}
}
