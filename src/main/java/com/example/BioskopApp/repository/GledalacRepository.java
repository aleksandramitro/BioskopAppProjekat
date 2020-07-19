package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Gledalac;

@Repository
public interface GledalacRepository extends JpaRepository<Gledalac, Long>{

	public Gledalac findByKorisnickoIme(String korisnickoIme);
	
}
