package com.ppshrimp.filmsystem.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Movie;
import com.ppshrimp.filmsystem.persistence.service.MovieService;
import com.ppshrimp.filmsystem.util.DateHelper;

@Controller
@RequestMapping("movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
    /*    
    @RequestMapping(value="/movie/{value}", method=RequestMethod.GET)
	public String getMovieById(@PathVariable String value, Model model) {
    	model.addAttribute("title", "电影详情");
    	Movie movie  = movieService.findOne(value);
    	model.addAttribute("movie", movie);
    	return "detail";
	}*/
	
	// /movie/detial?movieId=123
    @RequestMapping(value="/detail", method=RequestMethod.GET)
    public String getMovieById(@RequestParam("movieId") long movieId, Model model) {
    	model.addAttribute("title", "电影详情");
    	Movie movie  = movieService.findOneById(movieId);
    	model.addAttribute("movie", movie);
    	return "detail";
    }
    
    
    @RequestMapping(value="/allMv", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getAllMovies(){
    	List<Movie> movies  = movieService.findAll();
    	return movies;
	}
    
    // 获取正在上映的电影
    @RequestMapping(value="/onShowMv", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getAllOnShowMovies() {
        DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findAllOnShow(dh.getCurrentDate());
    	return movies;
	}
    
}
