package com.ppshrimp.filmsystem.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.service.CinemaService;

@Controller
@CrossOrigin
@RequestMapping(value = "/cinema")
public class CinemaContorller {
private static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private CinemaService cinemaService;
	
	// /cinema
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getCinemabyCity(
			@RequestParam(value="city", required=false, defaultValue="广州")String city) {
		return cinemaService.getCinemasByCity(city);
	}
	
	@RequestMapping(value="/allCinema", method=RequestMethod.GET)
	public @ResponseBody List<Cinema> getAllCinema(
			@RequestParam(value="city", required=false, defaultValue="广州")String city) {
		return cinemaService.getAllCinema();
	}
	
}
