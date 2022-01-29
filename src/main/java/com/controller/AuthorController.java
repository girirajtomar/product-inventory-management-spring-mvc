package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sessionmanagement.UserSession;

@Controller
public class AuthorController {
	@RequestMapping(value="/getallauthors", method=RequestMethod.GET)
	public ModelAndView homeGet(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchproduct");
		
		if(UserSession.getUser(req.getSession())==null) {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
