package com.example.BioskopApp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Karta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long rasporedId;
	
	@Column
	private Long gledalacId;
	
	@Column
	private Long salaId;
	
	@Column
	private int brojKarata;
	
	public Karta() {
		
		
	}

	public Karta(Long id, Long rasporedId, Long gledalacId, Long salaId, int brojKarata) {
		super();
		this.id = id;
		this.rasporedId = rasporedId;
		this.gledalacId = gledalacId;
		this.salaId = salaId;
		this.brojKarata = brojKarata;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRasporedId() {
		return rasporedId;
	}

	public void setRasporedId(Long rasporedId) {
		this.rasporedId = rasporedId;
	}

	public Long getGledalacId() {
		return gledalacId;
	}

	public void setGledalacId(Long gledalacId) {
		this.gledalacId = gledalacId;
	}

	public Long getSalaId() {
		return salaId;
	}

	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}

	public int getBrojKarata() {
		return brojKarata;
	}

	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}

	

	
	
}
