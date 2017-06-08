package com.ppshrimp.filmsystem.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshrimp.filmsystem.persistence.entity.Movie;
import com.ppshrimp.filmsystem.persistence.service.MovieService;
import com.ppshrimp.filmsystem.util.DateHelper;
import com.ppshrimp.filmsystem.util.MvHelper;

@Controller
@CrossOrigin
@RequestMapping("movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
    
    @RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public @ResponseBody Movie getMovieById(@PathVariable(name="id", required=true) Integer value) throws NullPointerException {
    	Movie movie  = movieService.findOneById(value.intValue());
        return movie;
	}
    
    @RequestMapping(value="/allMv", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getAllMovies() {
    	List<Movie> movies  = movieService.findAll();
    	return movies;
	}
    
    // 获取正在上映的电影
    @RequestMapping(value="/onShowMv", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getAllOnShowMovies(
			@RequestParam(value="num", required=false, defaultValue = "24") int num) {
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
    
/*    @RequestMapping(value="/topTen", method=RequestMethod.GET)
    public @ResponseBody List<Movie> getTopTenOnShowMovies(
    		@RequestParam(value="num", required=false, defaultValue = "24") int num) {
    	DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findTopTen(dh.getCurrentDate());
    	if (movies == null)
    		return new ArrayList<>();
    	if (movies != null && movies.size() <= num)
    		return movies;
    	else {
	    	return movies.subList(0, num);
    	}
    }*/
    
/*    @RequestMapping(value="/recommend", method=RequestMethod.GET)
    public @ResponseBody List<Movie> getRecommend(
    		@RequestParam(value="num", required=false, defaultValue = "24") int num) {
    	DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findRcecomment(dh.getCurrentDate());
    	if (movies == null) return new ArrayList<>();
    	else {
	    	if (movies.size() > num)
		    	movies =  movies.subList(0, num);
	    	Collections.sort(movies);
	    	return movies;
    	}
    }*/
    
    // movie/commingsoon?num=6
    @RequestMapping(value="/recommend", method=RequestMethod.GET)
    public @ResponseBody List<Movie> getRecommend(
    		@RequestParam(value="num", required=false, defaultValue = "6") int num) {
    	DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findRcecommend(dh.getCurrentDate());
    	if (movies == null) return new ArrayList<>();
    	else {
	    	if (movies.size() > num)
		    	movies =  movies.subList(0, num);
	    	return movies;
    	}
    }
    
    // movie/commingsoon?num=12
    @RequestMapping(value="/commingsoon", method=RequestMethod.GET)
    public @ResponseBody List<Movie> getCommingSoon(
    		@RequestParam(value="num", required=false, defaultValue = "24") int num) {
    	DateHelper dh = new DateHelper();
    	List<Movie> movies  = movieService.findCommingSoon(dh.getCurrentDate(), dh.getAfterCurrentDate(7));
    	if (movies == null) return new ArrayList<>();
    	else {
	    	if (movies.size() > num)
		    	movies =  movies.subList(0, num);
	    	Collections.sort(movies);
	    	return movies;
    	}
    }
    
    // movie/search?msg=xxx
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public @ResponseBody List<Movie> searchMovie(
    		@RequestParam(name="msg", required=true) String msg) {
    		return movieService.searchMovie(msg);
    }
    
}
