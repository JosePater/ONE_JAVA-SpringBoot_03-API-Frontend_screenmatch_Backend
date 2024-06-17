package com.josepaternina.screenmatch.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controlador
public class SerieController {

    @GetMapping("/series")
    public String mostrarMensaje() {
        return "Este es el primer mensaje";
    }
}
