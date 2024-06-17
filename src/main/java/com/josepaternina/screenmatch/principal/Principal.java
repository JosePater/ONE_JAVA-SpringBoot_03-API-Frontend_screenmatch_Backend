package com.josepaternina.screenmatch.principal;

import com.josepaternina.screenmatch.model.DatosSerie;
import com.josepaternina.screenmatch.model.DatosTemporadas;
import com.josepaternina.screenmatch.model.Episodio;
import com.josepaternina.screenmatch.model.Serie;
import com.josepaternina.screenmatch.repository.SerieRepository;
import com.josepaternina.screenmatch.service.ConsumoAPI;
import com.josepaternina.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ed51d11";
    private ConvierteDatos conversor = new ConvierteDatos();
    // private List<DatosSerie> datosSeries = new ArrayList<>(); // Se guardaba a nivel local
    List<Serie> seriesBuscadas;

    private SerieRepository repositorio;

    public Principal(SerieRepository repository) {
        this.repositorio = repository;
    }


    public void mostrarMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """ 
                    \n__________________________________
                    DIGITE UN NÚMERO SEGÚN SU BÚSQUEDA:
                    1 - Buscar series:
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    0 - Salir
                    
                    Opción:
                    """;

            System.out.print(menu);
            option = teclado.nextInt();
            teclado.nextLine();

            switch (option) {
                case 1:
                    this.buscarSerieWeb();
                    break;
                case 2:
                    this.buscarEpisodioPorSerie();
                    break;
                case 3:
                    this.listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Aplicación finalizada!!!");
                    break;
                default:
                    System.out.println("Opción inválida!");
            }
        }
    }

    // Obtener datos de una serie
    private DatosSerie getDatosSerie() {
        System.out.println("\nEscriba el nombre de la serie a buscar: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println("Json: " + json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    // Buscar Episodios
    private void buscarEpisodioPorSerie() {
        this.listarSeriesBuscadas(); // Lista de series buscadas
        System.out.println("\nEscribe el nombre de la serie que desea ver: ");
        var nombreSerieBuscada = teclado.nextLine(); // Nombre de la serie buscada

        Optional<Serie> serie = seriesBuscadas.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerieBuscada.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&Season=" + i + API_KEY);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada); // Agrega los episodios de la temporada i
            }
            temporadas.forEach(System.out::println);

            // Lista de episodios
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numeroTemporada(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }

    }

    // Buscar Serie
    private void buscarSerieWeb() {
        DatosSerie datos = this.getDatosSerie(); // Obtiene los datos de la serie buscada
        Serie serie = new Serie(datos);

        System.out.println("Serie --->: " + serie);

        if (datos.titulo() != null) {
            repositorio.save(serie); // Guardar en la base de datos
            // datosSeries.add(datos); // Se guardaba a nivel local
            System.out.println("Datos: " + datos);
            return;
        }
        System.out.println("Serie no encontrada");
    }

    // Mostrar series buscadas
    private void mostrarSeriesBuscadas() {
        // Se accede a toda la información de db de la tabla series
        seriesBuscadas = repositorio.findAll();

        if (seriesBuscadas.size() == 0) {
            System.out.println("Historial de series buscadas vacío!");
            return;
        }

        System.out.println("\nLista de series buscadas: cantidad: " + seriesBuscadas.size());

        seriesBuscadas.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    // Lista de series buscadas
    private void listarSeriesBuscadas() {
        seriesBuscadas = repositorio.findAll();
        seriesBuscadas.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

}
