// src/main/java/org/example/arcade/repository/ResultRepository.java
package org.example.arcade.repository;

import org.example.arcade.model.Result;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz DAO para persistir y recuperar resultados de partidas.
 * Respeta el principio de inversión de dependencias (D de SOLID):
 * la capa de servicio dependerá de esta interfaz, no de una implementación concreta.
 */
public interface ResultRepository {

    /**
     * Guarda un resultado de partida.
     * @param result entidad a guardar
     * @return el mismo objeto con ID asignado
     */
    Result save(Result result);

    /**
     * Busca un resultado por su identificador.
     * @param id identificador
     * @return opcional con el resultado si existe
     */
    Optional<Result> findById(Long id);

    /**
     * Recupera todos los resultados persistidos.
     * @return lista de resultados
     */
    List<Result> findAll();
}
