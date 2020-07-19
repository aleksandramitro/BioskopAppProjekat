package com.example.BioskopApp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Bioskop implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column
	private String brojTelefona;
	
	@Column
	private String email;
	
	@OneToMany(mappedBy = "bioskop" , fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Sala> bioskopskeSale = new HashSet<Sala>();
	
	@ManyToMany(mappedBy = "bioskopi" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Menadzer> menadzeri = new HashSet<Menadzer>();
	
	@OneToMany(mappedBy = "bioskop" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Raspored> rasporedOdrzavanjaFilmova = new HashSet<Raspored>();
	
	
	public Bioskop(Long id, String naziv, String adresa, String brojTelefona, String email, Set<Sala> bioskopskeSale,
			Set<Menadzer> menadzeri, Set<Raspored> rasporedOdrzavanjaFilmova) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.bioskopskeSale = bioskopskeSale;
		this.menadzeri = menadzeri;
		this.rasporedOdrzavanjaFilmova = rasporedOdrzavanjaFilmova;
	}

	public Bioskop() {
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Set<Sala> getBioskopskeSale() {
		return bioskopskeSale;
	}

	public void setBioskopskeSale(Set<Sala> bioskopskeSale) {
		this.bioskopskeSale = bioskopskeSale;
	}

	public Set<Menadzer> getMenadzeri() {
		return menadzeri;
	}

	public void setMenadzeri(Set<Menadzer> menadzeri) {
		this.menadzeri = menadzeri;
	}

	public Set<Raspored> getRasporedOdrzavanjaFilmova() {
		return rasporedOdrzavanjaFilmova;
	}

	public void setRasporedOdrzavanjaFilmova(Set<Raspored> rasporedOdrzavanjaFilmova) {
		this.rasporedOdrzavanjaFilmova = rasporedOdrzavanjaFilmova;
	}

	
	@Override
	public String toString() {
		return "Bioskop [id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona
				+ ", email=" + email + "]";
	}
	
	
}
