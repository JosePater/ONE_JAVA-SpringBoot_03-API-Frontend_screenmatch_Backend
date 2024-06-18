package com.josepaternina.screenmatch.service;

import com.josepaternina.screenmatch.dto.SerieDTO;
import com.josepaternina.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Servicio
public class SerieService {

    @Autowired
    private SerieRepository repository; // Inyección del repositorio

    public List<SerieDTO> obtenerTodasLasSeries() {
        // Conversión del tipo de datos Serie a SerieDTO
        return repository.findAll().stream().
                map(s -> new SerieDTO(s.getTitulo(), s.getTotalDeTemporadas(), s.getEvaluacion(), s.getGenero(), s.getSinopsis(), s.getPoster()))
                .collect(Collectors.toList());
    }
}
