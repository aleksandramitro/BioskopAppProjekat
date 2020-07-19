package com.example.BioskopApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Bioskop;
import com.example.BioskopApp.entity.Menadzer;
import com.example.BioskopApp.entity.Sala;
import com.example.BioskopApp.repository.BioskopRepository;

@Service
public class BioskopService {

	@Autowired
	private BioskopRepository bioskopRepository;
	
	public List<Bioskop> findAll() {
		
		List<Bioskop> bioskopi = bioskopRepository.findAll();
		return bioskopi;
	}
	
	public List<Bioskop> pronadjiNezaduzene(Menadzer menadzer) {
		
		List<Bioskop> bioskopi = bioskopRepository.findAll();
		
		for (Bioskop bioskop : bioskopi) {
			
			Set<Menadzer> menadzeri = bioskop.getMenadzeri();
			
			for (Menadzer menadzer2 : menadzeri) {
				
				if(menadzer2.getId() == menadzer.getId()) {
					
					bioskopi.remove(bioskop);
				}
			}
		}
		return bioskopi;
		
	}
	
	public List<Bioskop> pronadjiZaduzene(Menadzer menadzer) {
		
		List<Bioskop> bioskopi = bioskopRepository.findAll();
		
		for (Bioskop bioskop : bioskopi) {
			
			Set<Menadzer> menadzeri = bioskop.getMenadzeri();
			int p = menadzeri.size();
			int a = 0;
			
			for (Menadzer menadzer2 : menadzeri) {
				
				if(menadzer2.getId() != menadzer.getId()) {
					
					a++;
				}
			}
			if(a != p) {
				
				bioskopi.remove(bioskop);
			}
		}
		
		return bioskopi;
		
	}
	
	public void sacuvaj(Bioskop bioskop) {
		
		bioskopRepository.save(bioskop);
	}
	
	public void obrisiPoId(Long id) {
		
		bioskopRepository.deleteById(id);
	}
	
	public Bioskop findOne(Long id) {
		
		Bioskop bioskop = bioskopRepository.getOne(id);
		return bioskop;
	}
	
	public void delete(Long id) {
		
		bioskopRepository.deleteById(id);
	}
	
	public Bioskop create(Bioskop bioskop) {
		
		return null;
	}
	
	public Bioskop save(Bioskop bioskop) {
		
		return bioskopRepository.save(bioskop);
	}
	
	public Bioskop update(Bioskop bioskop) {
		
		Bioskop stari = bioskopRepository.getOne(bioskop.getId());
		if(stari == null) {
			
			return create(stari);
		}
		
		stari.setAdresa(bioskop.getAdresa());
		stari.setBioskopskeSale(bioskop.getBioskopskeSale());
		stari.setBrojTelefona(bioskop.getBrojTelefona());
		stari.setEmail(bioskop.getEmail());
		stari.setNaziv(bioskop.getNaziv());
		stari.setMenadzeri(bioskop.getMenadzeri());
		stari.setRasporedOdrzavanjaFilmova(bioskop.getRasporedOdrzavanjaFilmova());
		
		return save(bioskop);
	}
	
	public void delete(Bioskop bioskop) {
		
		bioskopRepository.delete(bioskop);
	}
	
}
