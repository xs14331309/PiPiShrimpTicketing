package com.ppshrimp.filmsystem.web.controller;


import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Movie;
import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.MovieService;
import com.ppshrimp.filmsystem.persistence.service.UserService;


@Controller
@RequestMapping(value = "/index")
public class IndexController {
	private static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	// 获取Index Page
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("username", "游客"); // 初始化信息
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipal() != null) // 更新信息
	       model.addAttribute("username", subject.getPrincipal().toString());
		
		model.addAttribute("title", "首页");
		List<Movie> movies = movieService.findAll();
		model.addAttribute("movies", movies);
		
		return "index";
	}
	
	// 登录请求
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String greeting(User user, Model model) {
    	try {
            String username = user.getUsername();
            String password = user.getPassword();
            
			User findUser = userService.findByName(username);
			log.debug("find out User?", !findUser.equals(null));
		    
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			
			try{
				subject.login(token);
				if (subject.isAuthenticated()) {
					System.out.println("OK");
					model.addAttribute("username", user.getUsername());
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
        return "redict:login";
    }
    
    // Shrio测试
    @RequestMapping(value="/hello") 
	public String getHello(){
		log.debug("get login page");
		return "hello";
	}   
}
