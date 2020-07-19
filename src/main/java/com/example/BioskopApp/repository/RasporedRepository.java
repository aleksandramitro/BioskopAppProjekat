package com.example.BioskopApp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Raspored;

@Repository
public interface RasporedRepository extends JpaRepository<Raspored, Long>{

	public List<Raspored> findByDatumVreme(LocalDateTime datumVreme);
}
