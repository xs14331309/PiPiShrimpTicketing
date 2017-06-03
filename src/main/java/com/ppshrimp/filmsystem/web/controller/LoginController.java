package com.ppshrimp.filmsystem.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.User;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.GET) 
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
	
	// 登录请求
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> greeting(@RequestBody User user, HttpServletResponse response)
        throws org.apache.shiro.authc.IncorrectCredentialsException {
    	Map<String, String> map = new HashMap<>();
    	try {
            String username = user.getUsername();
            String password = user.getPassword();
			/*User findUser = userService.findByName(username);
			log.debug("find out User?", !findUser.equals(null));*/
		    
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			
			subject.login(token);
			if (subject.isAuthenticated()) {
				map.put("result", "success");
				map.put("message", "success");
			}
			else {
				map.put("result", "fail");
				map.put("message", "未知错误");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "用户名或密码不正确");
		}
    	return map;
    }
}
