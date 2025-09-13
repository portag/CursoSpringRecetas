package com.emilio.recetas.repository;

import com.emilio.recetas.model.Receta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
	@Query("SELECT r FROM Receta r JOIN FETCH r.dificultad")
    List<Receta> findAllWithDificultad();
}
