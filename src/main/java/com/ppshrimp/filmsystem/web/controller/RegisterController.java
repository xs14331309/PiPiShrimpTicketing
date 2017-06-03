package com.ppshrimp.filmsystem.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.UserService;

@Controller
public class RegisterController {
	private static Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User getUser(User user) {
		return new User();
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
    public String getRegister(final User customer) {
		log.info("Register Page");
        return "register";
    }
	
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody User user) throws Exception {
    	Map<String, String> map = new HashMap<>();
    	User findUser = userService.findByName(user.getUsername()); 
		if (findUser == null) {
			userService.create(user);
			map.put("result", "success");
			map.put("message", "success");
		}
		else {
			map.put("result", "fail");
			map.put("message", "该用户已存在");
		}
		return map;
    }
}
