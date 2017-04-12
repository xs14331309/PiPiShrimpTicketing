package com.ppshrimp.filmsystem.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.UserService;

@Controller
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login") 
	public String getLogin(@ModelAttribute("user")User user){
		log.debug("get login page");
		return "login";
	}
	
    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greeting(User user, Model model) {
    	try { 
			User findUser = userService.findByName(user.getName());
			log.debug("find out User?", !findUser.equals(null));
		   
			if (!findUser.equals(null) && findUser.getPassword().equals(user.getPassword())) {
		        model.addAttribute("name", user.getName());
		        return "greeting";
			}
			// 密码错误
			else return "redirect:login";
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "login";
    }

	@RequestMapping(value="/greeting")
    public void sayHello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello ,!!!!</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }
}
