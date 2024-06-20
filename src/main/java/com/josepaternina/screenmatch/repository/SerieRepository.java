package com.josepaternina.screenmatch.repository;

import com.josepaternina.screenmatch.model.Categoria;
import com.josepaternina.screenmatch.model.Episodio;
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

    // Obtener las series más recientes (5)
    @Query("SELECT s FROM Serie s JOIN s.episodios e GROUP BY s ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientosMasRecientes();

    // Obtener episodios por el número de temporada
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}

