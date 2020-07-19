package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BioskopApp.entity.OcenjenFilm;
import com.example.BioskopApp.entity.OdgledanFilm;

public interface OcenjenFilmRepository extends JpaRepository<OcenjenFilm, Long>{

	
	public OcenjenFilm findByOdgledanFilm(OdgledanFilm odgledanFilm);
}
