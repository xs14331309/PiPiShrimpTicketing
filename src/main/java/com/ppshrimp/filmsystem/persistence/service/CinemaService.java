package com.ppshrimp.filmsystem.persistence.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshrimp.filmsystem.persistence.dao.CinemaDao;
import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;


@Service("cinemaService")
@Transactional
public class CinemaService {
	@Autowired
	private CinemaDao cinemaDao;
	
	// 获取所有Cinemas
	public List<Cinema> getAllCinema() {
		return cinemaDao.getAll();
	}
	
	// 根据用户城市查找电影院
	public List<Cinema> getCinemasByCity(String city) {
		return cinemaDao.getByCity(city);
	}
	
	// 查找用户附近电影院GPS
	public List<Cinema> getCinemasByPos(float lo, float la) {
		float threshold = 0.5f;
		int n = 3;
		List<Cinema> cinemas = null;
		do {
			cinemas = cinemaDao.getByPos(lo, la, threshold);
			threshold += 0.2f;
			n--;
		} while (n > 0 & (cinemas != null | cinemas.size() < 2));
		return cinemas;
	}
	
	// 查找某电影有余票的
	public List<CinemaMoviePos> getMoviesByCinemaId(long id) {
		return cinemaDao.getById(id);
	}
}
