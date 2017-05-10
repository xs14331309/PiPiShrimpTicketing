package com.ppshrimp.filmsystem.persistence.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public Movie findOneById(long id) {
		return movieDao.findOneById(id);
	}
	
	public Movie findOneByName(String name) {
		return movieDao.findOneByName(name);
	}
	
	public Movie findOne(String value) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(value);
		if (!isNum.matches()) {
			return movieDao.findOneByName(value);
		} else {
			return movieDao.findOneById(Long.parseLong(value));
		}
	}
}
