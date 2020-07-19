package com.example.BioskopApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	public List<Korisnik> findOneByKorisnickoIme(String korisnickoIme);
	
	public Korisnik findByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
	
	public Korisnik findByKorisnickoIme(String korisnickoIme);
}
