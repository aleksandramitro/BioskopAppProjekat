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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Sala implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int kapacitet;
	
	@Column
	private int oznaka;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Bioskop bioskop;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<Raspored> listaTerminskihProjekcija = new HashSet<Raspored>();
	
	public Sala(Long id, int kapacitet, int oznaka, Bioskop bioskop, Set<Raspored> listaTerminskihProjekcija) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznaka = oznaka;
		this.bioskop = bioskop;
		this.listaTerminskihProjekcija = listaTerminskihProjekcija;
	}

	public Sala(Sala sala) {
		
		this.id = sala.id;
		this.kapacitet = sala.kapacitet;
		this.oznaka = sala.oznaka;
		this.bioskop = sala.bioskop;
		this.listaTerminskihProjekcija = sala.listaTerminskihProjekcija;
	}
	
	public Sala() {
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getOznaka() {
		return oznaka;
	}

	public void setOznaka(int oznaka) {
		this.oznaka = oznaka;
	}
	

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	

	public Set<Raspored> getListaTerminskihProjekcija() {
		return listaTerminskihProjekcija;
	}

	public void setListaTerminskihProjekcija(Set<Raspored> listaTerminskihProjekcija) {
		this.listaTerminskihProjekcija = listaTerminskihProjekcija;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", kapacitet=" + kapacitet + ", oznaka=" + oznaka + "]";
	}
	
}
