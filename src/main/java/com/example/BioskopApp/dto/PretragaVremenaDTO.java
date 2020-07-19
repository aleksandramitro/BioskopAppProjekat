package com.example.BioskopApp.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PretragaVremenaDTO {

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime vreme;

	public PretragaVremenaDTO(LocalDateTime vreme) {
		super();
		this.vreme = vreme;
	}
	
	public PretragaVremenaDTO() {
		
		
	}

	public LocalDateTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalDateTime vreme) {
		this.vreme = vreme;
	}

	
	
}
