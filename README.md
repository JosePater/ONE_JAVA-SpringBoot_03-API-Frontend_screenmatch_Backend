# `03` - Java: creando tu primera API y conectándola al Front End

<p>
    <img src="https://img.shields.io/badge/STATUS-TERMINADO-red">
    <img src="https://img.shields.io/badge/SPRING BOOT-3.3.0-green">
    <img src="https://img.shields.io/badge/DATABASE-POSTGRES-blue">
</p>

## Descripción

Puesta en práctica de lo aprendido en `Oracle Next Education` (ONE) + `Alura LATAM`.

API de series de [`OMDb API`](https://www.omdbapi.com/)

Todas las series y episodios ha sido guardadas dentro de una base de datos local (`db`) de `OMDb API`.
Las opciones de los endpoints son consultas directamente de la `db` mediante el método GET.

Proyecto base:
(`02`) [ONE_JAVA-SpringBoot_02-JAVA-DB_screenmatch](https://github.com/JosePater/ONE_JAVA-SpringBoot_02-JAVA-DB_screenmatch/tree/03-mapping-relationship)

### Branches:

| Cantidad         | Nombre de la rama                |
|------------------|----------------------------------|
| Proyecto inicial | base-project_JAVA-DB_screenmatch |
| 01               | 01-transformation-to-an-API      |
| 02               | 02-Data-for-Frontend             |
| 03               | 03-mapping-website               |
| 04 (default)     | 04-Adding-more-details           |

### Endpoints

| Método | Ruta                                 | Descripción                                         |
|--------|--------------------------------------|-----------------------------------------------------|
| GET    | `/series`                            | Obtiene todas las series de la `db`                 |
| GET    | `/series/{id}`                       | Obtiene la serie por su id                          |
| GET    | `/series/top5`                       | Obtiene las 5 mejores series de la `db`             |
| GET    | `/series/lanzamientos`               | Obtiene las series más recientes de la  `db`        |
| GET    | `/series/{id}/temporadas/todas`      | Obtiene todos los episodios de todas las temporadas |
| GET    | `/{id}/temporadas/{numeroTemporada}` | Obtiene episodios por número de temporadas          |
| GET    | `/categoria/{nombreGenero}`          | Obtiene series según su categoría                   |
