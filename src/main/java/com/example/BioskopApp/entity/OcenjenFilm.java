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
import javax.persistence.OneToOne;

@Entity
public class OcenjenFilm implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private OdgledanFilm odgledanFilm;
	
	@Column
	private int ocena;
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Gledalac gledalac;

	
	public OcenjenFilm(Long id, OdgledanFilm odgledanFilm, int ocena, Gledalac gledalac) {
		super();
		this.id = id;
		this.odgledanFilm = odgledanFilm;
		this.ocena = ocena;
		this.gledalac = gledalac;
	}

	public OcenjenFilm() {
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}


	public Gledalac getGledalac() {
		return gledalac;
	}

	public void setGledalac(Gledalac gledalac) {
		this.gledalac = gledalac;
	}

	public OdgledanFilm getOdgledanFilm() {
		return odgledanFilm;
	}

	public void setOdgledanFilm(OdgledanFilm odgledanFilm) {
		this.odgledanFilm = odgledanFilm;
	}
	
	
}
