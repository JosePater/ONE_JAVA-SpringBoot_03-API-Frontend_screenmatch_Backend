package com.josepaternina.screenmatch.Controller;

import com.josepaternina.screenmatch.dto.SerieDTO;
import com.josepaternina.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Controlador
public class SerieController {

    @Autowired
    private SerieService service; // Inyección del servicio

    // Obtener todas las series de la base da datos
    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return service.obtenerTodasLasSeries();
    }

    // Obtener top 5 series (db)
    @GetMapping("/series/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return service.obtenerTop5Series();
    }

}