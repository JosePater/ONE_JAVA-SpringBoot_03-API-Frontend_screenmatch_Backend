package com.josepaternina.screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList("María","José","Hugo","Elver","Luis","Edel","Pablo","Eva");

        System.out.println("Lista ordenada alfabéticamente:");
        nombres.stream()
                .sorted() // Ordenar A-Z
                .limit(4) // Límite de 4
                .filter(n -> n.startsWith("E")) // Filtra nombres que empiecen con E
                .map(n -> n.toUpperCase()) // Mayúscuas
                .forEach(System.out::println);

        System.out.println("\nLista original: "+nombres);
    }
}
