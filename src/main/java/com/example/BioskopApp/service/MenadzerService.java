package com.example.BioskopApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Bioskop;
import com.example.BioskopApp.entity.Korisnik;
import com.example.BioskopApp.entity.Menadzer;
import com.example.BioskopApp.entity.Uloga;
import com.example.BioskopApp.repository.MenadzerRepository;

@Service
public class MenadzerService {

	@Autowired
	private MenadzerRepository menadzerRepository;
	
	public Menadzer dodaj(Korisnik korisnik) {
		
		Menadzer menadzer = new Menadzer();
		menadzer.setKorisnickoIme(korisnik.getKorisnickoIme());
		menadzer.setLozinka(korisnik.getLozinka());
		menadzer.setIme(korisnik.getIme());
		menadzer.setPrezime(korisnik.getPrezime());
		menadzer.setBrojTelefona(korisnik.getBrojTelefona());
		menadzer.setDatumRodjenja(korisnik.getDatumRodjenja());
		menadzer.setEmail(korisnik.getEmail());
		menadzer.setAktivan(korisnik.isAktivan());
		menadzer.setUloga(Uloga.MENADZER);
		return menadzerRepository.save(menadzer);
	}
	
	public Menadzer findOne(Long id) {
		
		Menadzer menadzer = menadzerRepository.getOne(id);
		return menadzer;
	}
	
	public List<Menadzer> findAllNonActiv() {
		
		List<Menadzer> svi = menadzerRepository.findAll();
		List<Menadzer> nepotvrdjeni = new ArrayList<Menadzer>();
		for (Menadzer menadzer : svi) {
			if(menadzer.isAktivan() == false) {
				nepotvrdjeni.add(menadzer);
			}
		}
		return nepotvrdjeni;
	}
	
	public Menadzer findByKorisnickoIme(String korisnickoIme) {
		
		return menadzerRepository.findByKorisnickoIme(korisnickoIme);
	}
	
	public void delete(Menadzer menadzer) {
		
		menadzerRepository.delete(menadzer);
	}
	
	public List<Menadzer> findAll() {
		
		List<Menadzer> svi = menadzerRepository.findAll();
		return svi;
	}
	
	public boolean zaduzenZaBioskop(Menadzer m , Bioskop b) {
		
		Set<Bioskop> bioskopi = m.getBioskopi();
		boolean t = false;
		for (Bioskop bioskop : bioskopi) {
			
			if(bioskop.getId() == b.getId()) {
				t = true;
				break;
				
			}
		}
		
		return t;
	}
	
	public void update(Menadzer menadzer) {
		
		Menadzer stari = menadzerRepository.getOne(menadzer.getId());
		stari.setAktivan(menadzer.isAktivan());
		stari.setBioskopi(menadzer.getBioskopi());
		stari.setBrojTelefona(menadzer.getBrojTelefona());
		stari.setDatumRodjenja(menadzer.getDatumRodjenja());
		stari.setEmail(menadzer.getEmail());
		stari.setIme(menadzer.getIme());
		stari.setKorisnickoIme(menadzer.getKorisnickoIme());
		stari.setLozinka(menadzer.getLozinka());
		stari.setPrezime(menadzer.getPrezime());
	}
} 
