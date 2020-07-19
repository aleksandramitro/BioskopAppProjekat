package com.example.BioskopApp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UlogovanKorisnik implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String korisnickoIme;
	
	@Column
	private String lozinka;
	
	public UlogovanKorisnik() {
		
		
	}

	public UlogovanKorisnik(Long id, String korisnickoIme, String lozinka) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	
	public UlogovanKorisnik(UlogovanKorisnik ulogovanKorisnik) {
		
		this.id = ulogovanKorisnik.id;
		this.korisnickoIme = ulogovanKorisnik.korisnickoIme;
		this.lozinka = ulogovanKorisnik.lozinka;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "UlogovanKorisnik [id=" + id + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}
	
}
