package com.ppshrimp.filmsystem.persistence.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshrimp.filmsystem.persistence.dao.MovieDao;
import com.ppshrimp.filmsystem.persistence.entity.Movie;



@Service("movieService")
@Transactional
public class MovieService {
	@Autowired
	private MovieDao movieDao;
	
	public MovieService(MovieDao dao) {
		movieDao = dao;
	}
	
	public List<Movie> findAll() {
		return movieDao.findAll();
	}
	
	public List<Movie> findAllOnShow(Date date) {
		return movieDao.findAllOnShow(date);
	}
	
	public Movie findOneById(long id) {
		return movieDao.findOneById(id);
	}
	
	public Movie findOneByName(String name) {
		return movieDao.findOneByName(name);
	}
	
	// ID(优先）或者名字查找
/*	public Movie findOne(String value) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(value);
		Movie movie = null;
		if (isNum.matches()) 
			movie =  movieDao.findOneById(Long.parseLong(value));
		if (movie == null)
			movie = movieDao.findOneByName(value);
        return movie;
	}*/
}
