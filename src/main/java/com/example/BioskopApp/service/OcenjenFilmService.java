package com.example.BioskopApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.OcenjenFilm;
import com.example.BioskopApp.entity.OdgledanFilm;
import com.example.BioskopApp.repository.OcenjenFilmRepository;

@Service
public class OcenjenFilmService {

	@Autowired
	private OcenjenFilmRepository ocenjenFilmRepository;
	
	public OcenjenFilm findByOdgledanFilm(OdgledanFilm odgledanFilm) {
		
		return ocenjenFilmRepository.findByOdgledanFilm(odgledanFilm);
	}
	
	public OcenjenFilm save(OcenjenFilm ocenjenFilm) {
		
		return ocenjenFilmRepository.save(ocenjenFilm);
	}
	
	public OcenjenFilm update(OcenjenFilm ocenjenFilm) {
		
		OcenjenFilm stari = ocenjenFilmRepository.getOne(ocenjenFilm.getId());
		stari.setGledalac(ocenjenFilm.getGledalac());
		stari.setOcena(ocenjenFilm.getOcena());
		stari.setOdgledanFilm(ocenjenFilm.getOdgledanFilm());
		
		
		
		return save(ocenjenFilm);
	}
	
}
