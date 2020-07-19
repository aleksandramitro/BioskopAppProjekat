package com.example.BioskopApp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Entity
public class Gledalac extends Korisnik implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "gledalac" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OdgledanFilm> odgledaniFilmovi = new HashSet<OdgledanFilm>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Raspored> rezervisaneKarte = new HashSet<Raspored>();
	
	@OneToMany(mappedBy = "gledalac" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<OcenjenFilm> ocenjeniFilmovi = new HashSet<OcenjenFilm>();
	
	
	
	public Gledalac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona,
			String email, Date datumRodjenja, Uloga uloga, boolean aktivan, Set<OdgledanFilm> odgledaniFilmovi,
			Set<Raspored> rezervisaneKarte, Set<OcenjenFilm> ocenjeniFilmovi) {
		super(id, korisnickoIme, lozinka, ime, prezime, brojTelefona, email, datumRodjenja, uloga, aktivan);
		this.odgledaniFilmovi = odgledaniFilmovi;
		this.rezervisaneKarte = rezervisaneKarte;
		this.ocenjeniFilmovi = ocenjeniFilmovi;
	}

	public Set<OdgledanFilm> getOdgledaniFilmovi() {
		return odgledaniFilmovi;
	}

	public void setOdgledaniFilmovi(Set<OdgledanFilm> odgledaniFilmovi) {
		this.odgledaniFilmovi = odgledaniFilmovi;
	}

	public Gledalac() {
		
		
	}

	public Set<OcenjenFilm> getOcenjeniFilmovi() {
		return ocenjeniFilmovi;
	}

	public void setOcenjeniFilmovi(Set<OcenjenFilm> ocenjeniFilmovi) {
		this.ocenjeniFilmovi = ocenjeniFilmovi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Raspored> getRezervisaneKarte() {
		return rezervisaneKarte;
	}

	public void setRezervisaneKarte(Set<Raspored> rezervisaneKarte) {
		this.rezervisaneKarte = rezervisaneKarte;
	}

	
	

	
	
	

}
