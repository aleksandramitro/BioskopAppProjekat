package com.example.BioskopApp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Raspored implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Film film;
	
	@Column
	private int cena;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime datumVreme;
	
	@ManyToMany(mappedBy = "listaTerminskihProjekcija" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<Sala> sale = new HashSet<Sala>();

	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Bioskop bioskop;
	
	@ManyToMany(mappedBy = "rezervisaneKarte", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<Gledalac> gledaoci = new HashSet<Gledalac>();
	

	public Raspored(Long id, Film film, int cena, LocalDateTime datumVreme, Set<Sala> sale, Bioskop bioskop,
			Set<Gledalac> gledaoci) {
		super();
		this.id = id;
		this.film = film;
		this.cena = cena;
		this.datumVreme = datumVreme;
		this.sale = sale;
		this.bioskop = bioskop;
		this.gledaoci = gledaoci;
	}

	public Raspored(Raspored raspored) {
		
		this.id = raspored.id;
		this.film = raspored.film;
		this.cena = raspored.cena;
		this.bioskop = raspored.bioskop;
		this.datumVreme = raspored.datumVreme;
		this.sale = raspored.sale;
		this.bioskop = raspored.bioskop;
		this.gledaoci = raspored.gledaoci;
	
		
	
	}
	
	public Raspored() {
		
		
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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
	

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	

	public LocalDateTime getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(LocalDateTime datumVreme) {
		this.datumVreme = datumVreme;
	}

	
	
	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<Gledalac> getGledaoci() {
		return gledaoci;
	}

	public void setGledaoci(Set<Gledalac> gledaoci) {
		this.gledaoci = gledaoci;
	}

	@Override
	public String toString() {
		return "Raspored [id=" + id + ", film=" + film + ", cena=" + cena + "]";
	}

	
	
	
}
