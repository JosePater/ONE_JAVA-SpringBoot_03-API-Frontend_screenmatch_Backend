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

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return service.obtenerTodasLasSeries();
    }

}