package com.josepaternina.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignora las propiedades de la api que no se han especificado aquí
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        // @JsonAlias: permite solo leer datos.  @JsonProperty: permite modificarlos
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Plot") String sinopsis,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Actors") String actores) {
}
