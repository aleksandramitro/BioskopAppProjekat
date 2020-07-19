package com.example.BioskopApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Gledalac;
import com.example.BioskopApp.entity.Korisnik;
import com.example.BioskopApp.entity.Uloga;
import com.example.BioskopApp.repository.GledalacRepository;

@Service
public class GledalacService {

	@Autowired
	private GledalacRepository gledalacRepository;
	
	public Gledalac dodaj(Korisnik korisnik) {
		
		Gledalac gledalac = new Gledalac();
		gledalac.setKorisnickoIme(korisnik.getKorisnickoIme());
		gledalac.setLozinka(korisnik.getLozinka());
		gledalac.setIme(korisnik.getIme());
		gledalac.setPrezime(korisnik.getPrezime());
		gledalac.setBrojTelefona(korisnik.getBrojTelefona());
		gledalac.setDatumRodjenja(korisnik.getDatumRodjenja());
		gledalac.setEmail(korisnik.getEmail());
		gledalac.setAktivan(korisnik.isAktivan());
		gledalac.setUloga(Uloga.GLEDALAC);
		return gledalacRepository.save(gledalac);
	}
	
	public Gledalac findOne(Long id) {
		
		Gledalac gledalac = gledalacRepository.getOne(id);
		return gledalac;
	}
	
	public Gledalac findByKorisnickoIme(String korisnickoIme) {
		
		return gledalacRepository.findByKorisnickoIme(korisnickoIme);
	}
	
	public Gledalac update(Gledalac gledalac) {
		
		Gledalac stari = gledalacRepository.getOne(gledalac.getId());
		stari.setOcenjeniFilmovi(gledalac.getOcenjeniFilmovi());
		stari.setOdgledaniFilmovi(gledalac.getOdgledaniFilmovi());
		stari.setRezervisaneKarte(gledalac.getRezervisaneKarte());
		
		return stari;
	}
}
