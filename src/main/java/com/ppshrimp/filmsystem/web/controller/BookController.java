package com.ppshrimp.filmsystem.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;
import com.ppshrimp.filmsystem.persistence.entity.Order;
import com.ppshrimp.filmsystem.persistence.entity.User;
import com.ppshrimp.filmsystem.persistence.service.CMPosService;
import com.ppshrimp.filmsystem.persistence.service.CinemaService;
import com.ppshrimp.filmsystem.persistence.service.OrderService;
import com.ppshrimp.filmsystem.util.ThHelper;


@Controller
@RequestMapping(value="/book")
public class BookController {
	private static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private CinemaService cinemaService;
    
	@Autowired
	private CMPosService cmPosService;
	
	@Autowired
	private OrderService orderService;
	
	// /book/cinema
	@RequestMapping(value="/cinema", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getAllCinema() {
		return cinemaService.getAllCinema();
	}
	
	// book/csearch?city=string
	@RequestMapping(value="/asearch/city/{cityname}", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getCinemasByAddr(
			@PathVariable("city") String city) {
		return cinemaService.getCinemasByAddr(city);
		
	}
	
	@RequestMapping(value="/asearch/pos/{lo}/{la}", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getCinemasByPos(
			@PathVariable("lo") float longitude, @PathVariable("la") float lattitude) {
		return cinemaService.getCinemasByPos(longitude, lattitude);
		
	}
	
	// book/csearch?cid=type.long&mid=type.long
	@RequestMapping(value="/tsearch", method=RequestMethod.GET)
	public @ResponseBody List<ThHelper> getTimesByCidAndMid(
			@RequestParam(name="cid", required=true) Integer Cid,
			@RequestParam(name="mid", required=true) Integer Mid) {
		try {
			long cid = Cid.longValue();
			long mid = Mid.longValue();
			System.out.println(cid + " " + mid);
			return cmPosService.searchTimesOfCAndM(cid, mid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 订票
	// book/buy/{12_13}
	@RequestMapping(value="/buy/{tnums}", method=RequestMethod.GET)
	public @ResponseBody List<Order> doBuy(@PathVariable String tnums, @ModelAttribute("order")Order order) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated())
			return null;
		String mvname = "银河护卫队";
		String ciname = "广州万达1";
        int hnum = 2;
        String[] list = tnums.split("_");
        for (String string : list) {
        	Order order2 = new Order();
        	int t = Integer.parseInt(string);
    		order2.setUsername(subject.getPrincipal().toString());
    		order2.setCiname(ciname);
    		order2.setMvname(mvname);
    		order2.setTnum(t);
    		order2.setHnum(hnum);
    		orderService.create(order2);
        }
		
		return orderService.getOrderByUserName(subject.getPrincipal().toString());
	}
}
