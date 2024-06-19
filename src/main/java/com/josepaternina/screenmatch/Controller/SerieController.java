package com.josepaternina.screenmatch.Controller;

import com.josepaternina.screenmatch.dto.SerieDTO;
import com.josepaternina.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Controlador
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service; // Inyección del servicio

    // Obtener todas las series de la base da datos
    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries() {
        return service.obtenerTodasLasSeries();
    }

    // Obtener top 5 series (db)
    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return service.obtenerTop5Series();
    }

    // Ver las series más recientes
    @GetMapping("/lanzamiento")
    public List<SerieDTO> lanzamientosMasRecientes() {
        return service.obtenerLazamientosMasRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO ObtenerSeriePorId(@PathVariable Long id) {
        return service.obtenerSeriePorId(id);
    }

}