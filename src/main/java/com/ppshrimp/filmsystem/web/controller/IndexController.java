package com.ppshrimp.filmsystem.web.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.UserService;


@Controller
public class IndexController {
	private static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index")
	public String getIndex(Model model) {
		model.addAttribute("name", "游客"); // 初始化信息
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipal() != null) // 更新信息
	       model.addAttribute("name", subject.getPrincipal().toString());
		
		return "index";
	}
	
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String greeting(User user, Model model) {
    	try {
            String name = user.getName();
            String password = user.getPassword();
            
			User findUser = userService.findByName(name);
			log.debug("find out User?", !findUser.equals(null));
		    
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			
			try{
				subject.login(token);
				if (subject.isAuthenticated()) {
					System.out.println("OK");
					model.addAttribute("name", user.getName());
			        return "index";
				}
				else {
					System.out.println("No");
					return "redirect:login";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*if (!findUser.equals(null) && findUser.getPassword().equals(user.getPassword())) {
		        model.addAttribute("name", user.getName());
		        return "greeting";
			}
			// 密码错误
			else return "redirect:login";*/
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "login";
    }
    
    @RequestMapping(value="/hello") 
	public String getHeloo(){
		log.debug("get login page");
		return "hello";
	}
    
}
