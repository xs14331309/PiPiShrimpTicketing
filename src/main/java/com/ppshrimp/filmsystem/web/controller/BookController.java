package com.ppshrimp.filmsystem.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.entity.Order;
import com.ppshrimp.filmsystem.persistence.service.CMPosService;
import com.ppshrimp.filmsystem.persistence.service.CinemaService;
import com.ppshrimp.filmsystem.persistence.service.OrderService;
import com.ppshrimp.filmsystem.util.DateHelper;
import com.ppshrimp.filmsystem.util.ThHelper;


@Controller
@CrossOrigin
@RequestMapping(value="/book")
public class BookController {
	private static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private CinemaService cinemaService;
    
	@Autowired
	private CMPosService cmPosService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/asearch/pos/{lo}/{la}", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getCinemasByPos(
			@PathVariable("lo") float longitude, @PathVariable("la") float lattitude) {
		return cinemaService.getCinemasByPos(longitude, lattitude);
		
	}
	
	// book/csearch?cid=type.long&mid=type.long
	@RequestMapping(value="/tsearch/", method=RequestMethod.GET)
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
	
	@RequestMapping(value="/psearch/{cid}/{mid}/{tnum}/{hnum}", method=RequestMethod.GET)
	public @ResponseBody Map<String, String> getPosByCidMidTumHum(
			@PathVariable("cid") long cid, @PathVariable("mid") long mid,
			@PathVariable("tnum") int tnum, @PathVariable("hnum") int hnum) {
		Map<String, String> map = new HashMap<>();
		try {
			String pos = cmPosService.searchPosByCidMidTnumHnum(cid, mid, tnum, hnum);
			if (pos == null) {
				map.put("result", "fail");
				return map;
			} else {
				map.put("result", "success");
				map.put("pos", pos);
				return map;
			}
		} catch (Exception e) {
			map.put("result", "error");
			return map;
		} 
	}
	
	@RequestMapping(value="/confirm", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> confirmOrder(
			@RequestParam("username") String username,
			@RequestParam("cid") Long cid,
			@RequestParam("mid") Long mid,
			@RequestParam("hnum") Integer hnum,
			@RequestParam("tnum") Integer tnum,
			@RequestParam("seat") String seatsString) {
		Map<String, String> map = new HashMap<>();
		try{
			String[] seats = seatsString.split("_");
			List<Order> olist = new ArrayList<>();
			String pos = cmPosService.searchPosByCidMidTnumHnum(cid, mid, tnum, hnum);
			float price = cmPosService.searchPriceByCidMidTnumHnum(cid, mid, tnum, hnum);
			StringBuilder strBuilder = new StringBuilder(pos);
			for (String seat : seats) {
				int s = Integer.parseInt(seat);
	    		if (strBuilder.charAt(s) == '0') {
	    			strBuilder.setCharAt(s, '1');
					Order order = new Order();
		    		//order2.setUsername(subject.getPrincipal().toString());
		        	order.setUsername(username);
		        	order.setCinemaId(cid);
		        	order.setMovieId(mid);
		        	order.setTnum(tnum);
		        	order.setHnum(hnum);
		        	order.setSeat(s);
		        	order.setPrice(price);
	    			olist.add(order);
	    			}
	    		else {
	    			map.put("result", "fail");
	    			map.put("message", "座位已占用");
	    			return map;
	    		}
			}
			for (int i = 0; i < olist.size(); i++) {
				orderService.create(olist.get(i));	    
			}
			cmPosService.modifyPosByCidMidTnumHnum(strBuilder.toString(), cid, mid, tnum, hnum);
			map.put("result", "success");
			//map.put("price", )
			return map;

		} catch (Exception e) {
			//e.printStackTrace();
			map.put("result", "fail");
			return map;
		}
	}
}
