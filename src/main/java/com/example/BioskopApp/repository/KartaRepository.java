package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Karta;

@Repository
public interface KartaRepository extends JpaRepository<Karta,Long>{

	public Karta findByGledalacIdAndRasporedId(Long gledalacId,Long rasporedId);
}
