package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.persistence.UserDatabase;
import com.sessionmanagement.UserSession;

@Controller
public class UserController {
	// -------------------------- LOGIN MANAGEMENT----------------------------------------------------
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		if(UserSession.getUser(req.getSession())!=null) {
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginPost(@ModelAttribute("userForm") User user, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		// if any field is null then render login page
		if(user.getUsername() == null || user.getPassword()==null) {
			return mv;
		}
		
		if(UserDatabase.isExist(user)) {
			UserSession.saveUserInSession(req.getSession(), user);
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	
	// -------------------------- REGISTER MANAGEMENT----------------------------------------------------
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerGet() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registerPost(@ModelAttribute("userForm") User user,HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		// if any field is null then render login page
		if(user.getUsername() == null || user.getPassword()==null) {
			mv.setViewName("redirect:register");
			return mv;
		}
		
		if(!UserDatabase.isExist(user)) {
			UserDatabase.createUser(user);
			mv.setViewName("redirect:register");
		}
		return mv;
	}
	
	// -------------------------- LOGOUT MANAGEMENT----------------------------------------------------
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logoutGet(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		UserSession.removeUserFromSession(req.getSession());
		
		return mv;
	}
	
	

}
