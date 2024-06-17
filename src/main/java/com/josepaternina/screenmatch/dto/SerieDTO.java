package com.josepaternina.screenmatch.dto;

import com.josepaternina.screenmatch.model.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDTO(
        String titulo,
        Integer totalDeTemporadas,
        Double evaluacion,
        Categoria genero,
        String sinopsis,
        String poster) {
}
