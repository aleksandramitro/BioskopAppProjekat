package com.example.BioskopApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.OdgledanFilm;
import com.example.BioskopApp.repository.OdgledanFilmRepository;

@Service
public class OdgledanFilmService {

	@Autowired
	private OdgledanFilmRepository odgledanFilmRepository;
	
	public OdgledanFilm findOne(Long id) {
		
		return odgledanFilmRepository.getOne(id);
	}
	
	public void save(OdgledanFilm odgledanFilm) {
		
		odgledanFilmRepository.save(odgledanFilm);
	}
}
