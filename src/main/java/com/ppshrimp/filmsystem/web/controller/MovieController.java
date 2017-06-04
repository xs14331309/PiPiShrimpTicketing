package com.ppshrimp.filmsystem.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
    
    @RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public @ResponseBody Movie getMovieById(@PathVariable(name="id", required=true) Integer value) throws NullPointerException {
    	Movie movie  = movieService.findOneById(value.intValue());
        return movie;
	}
	
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
	public @ResponseBody List<Movie> getAllOnShowMovies(
			@RequestParam(value="num", required=false, defaultValue = "1") int num) {
        DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findAllOnShow(dh.getCurrentDate());
    	if (movies == null)
    		return new ArrayList<>();
    	if (movies != null && movies.size() <= num)
    		return movies;
    	else {
	    	return movies.subList(0, num);
    	}
	}
    
    @RequestMapping(value="/topTen", method=RequestMethod.GET)
    public @ResponseBody List<Movie> getTopTenOnShowMovies(
    		@RequestParam(value="num", required=false, defaultValue = "10") int num) {
    	DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findTopTen(dh.getCurrentDate());
    	if (movies == null)
    		return new ArrayList<>();
    	if (movies != null && movies.size() <= num)
    		return movies;
    	else {
	    	return movies.subList(0, num);
    	}
    }
    
}
