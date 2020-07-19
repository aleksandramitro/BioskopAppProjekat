package com.example.BioskopApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.UlogovanKorisnik;
import com.example.BioskopApp.repository.UlogovanKorisnikRepository;


@Service
public class UlogovanKorisnikService {

	@Autowired
	private UlogovanKorisnikRepository ulogovanKorisnikRepository;
	
	public void save(UlogovanKorisnik ulogovanKorisnik) {
		
		ulogovanKorisnikRepository.save(ulogovanKorisnik);
	}
	
	public UlogovanKorisnik getTrenutniKorisnik() {
		
		List<UlogovanKorisnik> ulogovaniKorisnici = ulogovanKorisnikRepository.findAll();
		if(ulogovaniKorisnici.isEmpty()) {
			
			return null;
		}
		return ulogovaniKorisnici.get(0);
	}
	
	public void deleteAll() {
		
		ulogovanKorisnikRepository.deleteAll();
	}
	
	
}
