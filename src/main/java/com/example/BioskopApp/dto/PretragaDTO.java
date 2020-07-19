package com.example.BioskopApp.dto;

import java.util.Date;

public class PretragaDTO {

	private String naziv;
	private String zanr;
	private String opis;
	public int cena;
	public double ocena;
	public Date vreme;
	
	public PretragaDTO() {
		
		
	}

	public PretragaDTO(String naziv, String zanr, String opis, int cena, double ocena, Date vreme) {
		super();
		this.naziv = naziv;
		this.zanr = zanr;
		this.opis = opis;
		this.cena = cena;
		this.ocena = ocena;
		this.vreme = vreme;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Date getVreme() {
		return vreme;
	}

	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}

	
	
	
}
