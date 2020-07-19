package com.example.BioskopApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Raspored;
import com.example.BioskopApp.repository.RasporedRepository;

@Service
public class RasporedService {

	@Autowired
	private RasporedRepository rasporedRepository;
	
	public Raspored findOne(Long id) {
		
		return rasporedRepository.getOne(id);
	}
	
	public Raspored update(Raspored raspored) {
		
		Raspored stari = rasporedRepository.getOne(raspored.getId());
		stari.setBioskop(raspored.getBioskop());

		stari.setCena(raspored.getCena());
		stari.setDatumVreme(raspored.getDatumVreme());
		stari.setFilm(raspored.getFilm());
		stari.setSale(raspored.getSale());
		return save(stari);
	}
	
	public Raspored save(Raspored raspored) {
		
		return rasporedRepository.save(raspored);
	}
	
	public List<Raspored> nedovrseni() {
		
		List<Raspored> rasporedi = rasporedRepository.findAll();
		List<Raspored> nedovrseni = new ArrayList<Raspored>();
		
		for (Raspored raspored : rasporedi) {
			
			if(raspored.getBioskop() == null) {
				
				nedovrseni.add(raspored);
			}
		}
		
		return nedovrseni;
	}
	
	public List<Raspored> findAll() {
		
		return rasporedRepository.findAll();
	}
	
	public List<Raspored> findByDatumVreme(LocalDateTime datumVreme) {
		
		return rasporedRepository.findByDatumVreme(datumVreme);
	}
	
	public void delete(Raspored raspored) {
		
		rasporedRepository.delete(raspored);
	}
}
