package com.ppshrimp.filmsystem.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ppshrimp.filmsystem.persistence.entity.User;

@Controller
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login") 
	public String getLogin(@ModelAttribute("user")User user){
		log.debug("get login page");
		return "login";
	}
    
	@RequestMapping(value="/loginOut")
	public String getLoginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		// 暂时没有loginout页面
        return "redirect:index";
	}
}
