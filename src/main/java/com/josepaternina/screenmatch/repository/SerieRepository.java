package com.josepaternina.screenmatch.repository;

import com.josepaternina.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio                              <Entidad, Type primaryKey>
public interface SerieRepository extends JpaRepository<Serie, Long> {
}

