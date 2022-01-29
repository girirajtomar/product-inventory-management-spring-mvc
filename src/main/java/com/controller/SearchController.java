package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.BuyerRequest;
import com.model.TShirt;
import com.model.User;
import com.persistence.TShirtDatabase;
import com.sessionmanagement.UserSession;

@Controller
public class SearchController {
	
	@RequestMapping(value="search", method=RequestMethod.POST)
	public ModelAndView searchPost(@ModelAttribute("searchForm") BuyerRequest request, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchproduct");
				
		if(UserSession.getUser(req.getSession())==null) {
			mv.setViewName("redirect:login");
		}
		
		List<TShirt> tshirtList = TShirtDatabase.getDataFromDB(request);
		System.out.println(tshirtList);
		TShirt[] tshirtArr = listToArray(tshirtList);
		
		mv.addObject("tshirtArr",tshirtArr);
		return mv;
	}
	
	static TShirt[] listToArray(List<TShirt> tshirtList) {
		TShirt[] tshirtArr = new TShirt[tshirtList.size()];
		for(int i=0;i<tshirtList.size();i++) {
			tshirtArr[i] = tshirtList.get(i);
		}
		return tshirtArr;
	}

}
