package com.example.BioskopApp.dto;

public class SortiranjeDTO {

	private Sortiranje sortiranje;

	public SortiranjeDTO(Sortiranje sortiranje) {
		super();
		this.sortiranje = sortiranje;
	}
	
	public SortiranjeDTO() {
		
		
	}

	public Sortiranje getSortiranje() {
		return sortiranje;
	}

	public void setSortiranje(Sortiranje sortiranje) {
		this.sortiranje = sortiranje;
	}
	
}
