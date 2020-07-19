package com.example.BioskopApp.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Bioskop;
import com.example.BioskopApp.entity.Sala;


@Repository
public interface BioskopRepository extends JpaRepository<Bioskop, Long>{

	
	
}
