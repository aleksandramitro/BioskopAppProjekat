package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Menadzer;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long>{

	public Menadzer findByKorisnickoIme(String korisnickoIme);
}
