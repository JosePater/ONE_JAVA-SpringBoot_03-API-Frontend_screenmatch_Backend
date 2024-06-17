package com.josepaternina.screenmatch.Controller;

import com.josepaternina.screenmatch.dto.SerieDTO;
import com.josepaternina.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController // Controlador
public class SerieController {

    @Autowired
    private SerieRepository repository; // Inyección del repositorio

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        // Conversión del tipo de datos Serie a SerieDTO
        return repository.findAll().stream().
                map(s -> new SerieDTO(s.getTitulo(), s.getTotalDeTemporadas(), s.getEvaluacion(), s.getGenero(), s.getSinopsis(), s.getPoster()))
                .collect(Collectors.toList());
    }

}