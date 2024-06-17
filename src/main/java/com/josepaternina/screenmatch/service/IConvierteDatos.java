package com.josepaternina.screenmatch.service;

public interface IConvierteDatos {
    // Tipos de datos genéricos
    <T> T obtenerDatos(String json, Class<T> clase);
}
