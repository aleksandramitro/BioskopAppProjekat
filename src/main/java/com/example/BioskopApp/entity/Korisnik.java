package com.example.BioskopApp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column
	protected String korisnickoIme;
	
	@Column
	protected String lozinka;
	
	@Column
	protected String ime;
	
	@Column
	protected String prezime;
	
	@Column
	protected String brojTelefona;
	
	@Column
	protected String email;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date datumRodjenja;
	
	@Column
	protected Uloga uloga;
	
	@Column
	protected boolean aktivan;

	public Korisnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona,
			String email, Date datumRodjenja, Uloga uloga, boolean aktivan) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.aktivan = aktivan;
	}
	
	public Korisnik() {
		
		
	}
	
	public Korisnik(Korisnik korisnik) {
		
		this.id = korisnik.id;
		this.korisnickoIme = korisnik.korisnickoIme;
		this.lozinka = korisnik.lozinka;
		this.ime = korisnik.ime;
		this.prezime = korisnik.prezime;
		this.brojTelefona = korisnik.brojTelefona;
		this.email = korisnik.email;
		this.datumRodjenja = korisnik.datumRodjenja;
		this.uloga = korisnik.uloga;
		this.aktivan = korisnik.aktivan;
		
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

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime
				+ ", prezime=" + prezime + ", brojTelefona=" + brojTelefona + ", email=" + email + ", datumRodjenja="
				+ datumRodjenja + ", uloga=" + uloga + ", aktivan=" + aktivan + "]";
	}
	
	
}
