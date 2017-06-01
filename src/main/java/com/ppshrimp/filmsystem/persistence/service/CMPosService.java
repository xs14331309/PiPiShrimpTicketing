package com.ppshrimp.filmsystem.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshrimp.filmsystem.persistence.dao.CMPosDao;
import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;
import com.ppshrimp.filmsystem.util.ThHelper;

@Service("cmPosService")
@Transactional
public class CMPosService {
	@Autowired
	private CMPosDao cmPosDao;
	
	public List<ThHelper> searchTimesOfCAndM(long cid, long mid) {
		List<CinemaMoviePos> cmPos = cmPosDao.getAllbyCM(cid, mid);
		List<ThHelper> ths = new ArrayList<>();
	    for (CinemaMoviePos cmp : cmPos) {
	    	ths.add(new ThHelper(cmp));
	    }
	    return ths;
	}
	
}
