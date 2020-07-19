package com.example.BioskopApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Korisnik;
import com.example.BioskopApp.entity.Uloga;
import com.example.BioskopApp.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public void registracija(Korisnik korisnik) {
		
		if(korisnikRepository.findOneByKorisnickoIme(korisnik.getKorisnickoIme())!=null) {
			
			
			
		} else {
			
			korisnikRepository.save(korisnik);
			
		}
		
	}
	
	public void delete(Long id) {
		
		Korisnik korisnik = this.korisnikRepository.getOne(id);
		korisnikRepository.delete(korisnik);
		
	}
	
	public Korisnik login(String korisnickoIme,String lozinka) {
		
		return korisnikRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
	}
	
	public Korisnik findByKorisnickoIme(String korisnickoIme) {
		
		return korisnikRepository.findByKorisnickoIme(korisnickoIme);
	}
	
	public boolean proveraListeKorisnika() {
		
		List<Korisnik> korisnici = korisnikRepository.findAll();
		
		if(korisnici.isEmpty()) {
			
			return false;
		} else {
			
			return true;
		}
	}
	
	public List<Korisnik> findAllNonActivManagers() {
		
		List<Korisnik> svi = korisnikRepository.findAll();
		List<Korisnik> neaktivni = new ArrayList<Korisnik>();
		for (Korisnik korisnik : svi) {
			
			if(korisnik.isAktivan() == false && korisnik.getUloga().equals(Uloga.MENADZER)) {
				neaktivni.add(korisnik);
			}
		}
		return neaktivni;
	}
	
	public Korisnik findOne(Long id) {
		
		Korisnik korisnik = korisnikRepository.getOne(id);
		return korisnik;
	}
}
