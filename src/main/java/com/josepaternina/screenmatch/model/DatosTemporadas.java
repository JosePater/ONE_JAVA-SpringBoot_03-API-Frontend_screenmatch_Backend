package com.josepaternina.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Ignora los datos que fueron mapeados aquí
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTemporadas(
        @JsonAlias("Season") Integer numeroTemporada,
        @JsonAlias("Episodes") List<DatosEpisodio> episodios
) {
}
