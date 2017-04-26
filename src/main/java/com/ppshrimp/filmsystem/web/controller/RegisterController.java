package com.ppshrimp.filmsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/register")
    public String getRegister(final User customer) {
		log.info("Register Page");
        return "register";
    }
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String finishRegister(@ModelAttribute("user")User user) {
		try{
			User findUser = userService.findByName(user.getName()); 
			if (findUser == null) {
				userService.create(user);
				return "login";
			}
			else {
				// Add err message to user 
				//
				return "redirect:register";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "regeister";
	}
}
