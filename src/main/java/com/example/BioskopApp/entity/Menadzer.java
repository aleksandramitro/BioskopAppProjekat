package com.example.BioskopApp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Menadzer extends Korisnik implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<Bioskop> bioskopi = new HashSet<Bioskop>();

	
	
	

	public Menadzer(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona,
			String email, Date datumRodjenja, Uloga uloga, boolean aktivan, Set<Bioskop> bioskopi) {
		super(id, korisnickoIme, lozinka, ime, prezime, brojTelefona, email, datumRodjenja, uloga, aktivan);
		this.bioskopi = bioskopi;
	}


	public Menadzer() {
		
		
	}

	
	public Set<Bioskop> getBioskopi() {
		return bioskopi;
	}


	public void setBioskopi(Set<Bioskop> bioskopi) {
		this.bioskopi = bioskopi;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
