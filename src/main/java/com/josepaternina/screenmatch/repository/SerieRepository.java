package com.josepaternina.screenmatch.repository;

import com.josepaternina.screenmatch.model.Categoria;
import com.josepaternina.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Repositorio                              <Entidad, Type primaryKey>
public interface SerieRepository extends JpaRepository<Serie, Long> {
    // Busca el nombre de la serie con partes del mismo (nombre no completo)
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    // Top 5 mejores series calificadas
    List<Serie> findTop5ByOrderByEvaluacionDesc();

    // Buscar series por categorías
    List<Serie> findByGenero(Categoria categoria);

    // Consultas JPQL (Java Persistence Query Language)        :variablePorParametro
    @Query("SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :totalTemporada AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemporadaYEvaluacion(int totalTemporada, double evaluacion);
}

