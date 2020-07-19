package com.example.BioskopApp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class OdgledanFilm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Film film;
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Gledalac gledalac;
	
	@OneToOne(mappedBy = "odgledanFilm" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private OcenjenFilm ocenjenFilm;
	

	public OdgledanFilm(Long id, Film film, Gledalac gledalac , OcenjenFilm ocenjenFilm) {
		super();
		this.id = id;
		this.film = film;
		this.gledalac = gledalac;
		this.ocenjenFilm = ocenjenFilm;
	}

	public OdgledanFilm() {
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	

	public Gledalac getGledalac() {
		return gledalac;
	}

	public void setGledalac(Gledalac gledalac) {
		this.gledalac = gledalac;
	}

	public OcenjenFilm getOcenjenFilm() {
		return ocenjenFilm;
	}

	public void setOcenjenFilm(OcenjenFilm ocenjenFilm) {
		this.ocenjenFilm = ocenjenFilm;
	}
	
	
}
