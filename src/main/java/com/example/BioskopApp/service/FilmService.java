package com.example.BioskopApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Film;
import com.example.BioskopApp.repository.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	public List<Film> findAll() {
		
		List<Film> filmovi = filmRepository.findAll();
		return filmovi;
		
	}
	
	public Film findOne(Long id) {
		
		return filmRepository.getOne(id);
	}
	
	
	public Film save(Film film) {
		
		return filmRepository.save(film);
	}
	
	public Film update(Film film) {
		
		Film stari = filmRepository.getOne(film.getId());
		stari.setNaziv(film.getNaziv());
		stari.setOcena(film.getOcena());
		stari.setOpis(film.getOpis());
		stari.setTrajanje(film.getTrajanje());
		stari.setZanr(film.getZanr());
		
		return save(stari);
	
	}
	
}
