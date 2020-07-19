package com.example.BioskopApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Sala;
import com.example.BioskopApp.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;
	
	public Sala deleteById(Long id) {
		
		Sala sala = salaRepository.getOne(id);
		salaRepository.deleteById(id);
		
		return sala;
	}
	
	public Sala sacuvaj(Sala sala) {
		
		return salaRepository.save(sala);
	}
	
	public Sala findOne(Long id) {
		
		return salaRepository.getOne(id);
	}
	
	public Sala create(Sala sala) {
		
		return null;
	}
	
	public Sala update(Sala sala) {
		
		Sala staraSala = salaRepository.getOne(sala.getId());
		if(staraSala == null) {
			
			return create(sala);
		}
		staraSala.setBioskop(sala.getBioskop());
		staraSala.setKapacitet(sala.getKapacitet());
		staraSala.setListaTerminskihProjekcija(sala.getListaTerminskihProjekcija());
		staraSala.setOznaka(sala.getOznaka());
		
		return sacuvaj(sala);
	}
	
	public void delete(Sala sala) {
		
		salaRepository.delete(sala);
	}
	public List<Sala> findAll() {
		
		return salaRepository.findAll();
	}
} 
