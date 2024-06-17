package com.josepaternina.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity // JPA la reconoce como una entidad
@Table(name = "series") // Nombre de la tabla, sin esto el nombre sería el mismo que la clase
public class Serie {
    @Id // Identificador: llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La misma entidad se encarga de la generación del id
    private Long id;
    @Column(unique = true) // Nombre único (Sin repetir)
    private String titulo;
    private Integer totalDeTemporadas;
    private Double evaluacion;
    @Enumerated(EnumType.STRING) // Lista de otros atributos (Categoría)
    private Categoria genero;
    private String sinopsis;
    private String poster;
    private String actores;
    // Una Serie puede tener muchos Episodios
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Llave foránea: serie de la tabla Episodio
    private List<Episodio> episodios; // Para la relación con la tabla Episodio

    public Serie() {
    }

    public Serie(DatosSerie datosSerie) {
        this.titulo = datosSerie.titulo();
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis();
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this)); // Vinculación entre tablas
        this.episodios = episodios;
    }

    // To string
    @Override
    public String toString() {
        return "Género=" + genero + ", Título='" + titulo + '\'' + ", TotalDeTemporadas=" + totalDeTemporadas + ", Evaluación=" + evaluacion + ", Sinopsis='" + sinopsis + '\'' + ", Poster='" + poster + '\'' + ", Actores='" + actores;
    }
}
