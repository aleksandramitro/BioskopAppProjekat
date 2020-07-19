package com.example.BioskopApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.UlogovanKorisnik;

@Repository
public interface UlogovanKorisnikRepository extends JpaRepository<UlogovanKorisnik,Long> {

	public List<UlogovanKorisnik> findAll();
}
