package com.example.BioskopApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BioskopApp.entity.Karta;
import com.example.BioskopApp.repository.KartaRepository;

@Service
public class KartaService {

	@Autowired
	private KartaRepository kartaRepository;
	
	public void save(Karta karta) {
		
		kartaRepository.save(karta);
	}
	
	public Karta findByGledalacIdAndRasporedId(Long gledalacId,Long rasporedId) {
		
		return kartaRepository.findByGledalacIdAndRasporedId(gledalacId, rasporedId);
	}
	
	public void delete(Karta karta) {
		
		kartaRepository.delete(karta);
	}
}
