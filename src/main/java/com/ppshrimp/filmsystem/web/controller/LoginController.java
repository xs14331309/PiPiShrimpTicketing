package com.ppshrimp.filmsystem.web.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.UserService;

@Controller
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST) 
	public String doLogin(@ModelAttribute("user")User user){
		log.debug("Dologin");
		try {
			User findUser = userService.findByName(user.getName());
			if (findUser == null) {
				userService.create(user);
				return "greeting";
			} else {
				return "login";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public String getLogin(@ModelAttribute("user")User user){
		log.debug("get login page");
		return "login";
	}
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

	@RequestMapping("/hello")
    public void sayHello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello ,!!!!</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }
}
