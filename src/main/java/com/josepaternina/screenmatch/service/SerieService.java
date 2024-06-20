package com.josepaternina.screenmatch.service;

import com.josepaternina.screenmatch.dto.EpisodioDTO;
import com.josepaternina.screenmatch.dto.SerieDTO;
import com.josepaternina.screenmatch.model.Serie;
import com.josepaternina.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Servicio
public class SerieService {

    @Autowired
    private SerieRepository repository; // Inyección del repositorio

    // Obtener todas las series
    public List<SerieDTO> obtenerTodasLasSeries() {
        return convertirASerieDTO(repository.findAll());
    }

    // Obtener las 5 mejores series
    public List<SerieDTO> obtenerTop5Series() {
        return convertirASerieDTO(repository.findTop5ByOrderByEvaluacionDesc());
    }

    // Convertir tipo de datos Serie a SerieDTO
    public List<SerieDTO> convertirASerieDTO(List<Serie> serie) {
        return serie.stream().
                map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalDeTemporadas(), s.getEvaluacion(),
                        s.getGenero(), s.getSinopsis(), s.getPoster(), s.getActores()))
                .collect(Collectors.toList());
    }

    // Lanzamientos más recientes
    public List<SerieDTO> obtenerLazamientosMasRecientes() {
        return convertirASerieDTO(repository.lanzamientosMasRecientes());
    }

    // Obtener Serie por Id
    public SerieDTO obtenerSeriePorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalDeTemporadas(), s.getEvaluacion(),
                    s.getGenero(), s.getSinopsis(), s.getPoster(), s.getActores());
        }

        return null;
    }

    // Obtener todos los episodios de todas las temporadas
    public List<EpisodioDTO> obtenerTodasLasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(),
                    e.getNumeroEpisodio())).collect(Collectors.toList());
        }
        return null;
    }

}
