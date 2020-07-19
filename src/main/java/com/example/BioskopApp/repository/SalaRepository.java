package com.example.BioskopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioskopApp.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{

}
