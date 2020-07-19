package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.OdgledanFilm;

@Repository
public interface OdgledanFilmRepository extends JpaRepository<OdgledanFilm, Long>{

}
