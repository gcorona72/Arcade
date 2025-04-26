// src/main/java/org/example/arcade/service/GameService.java
package org.example.arcade.service;

import org.example.arcade.model.Move;
import org.example.arcade.model.Position;
import org.example.arcade.model.Solution;

import java.util.List;
import java.util.Map;

/**
 * Interfaz que define las operaciones de negocio de un juego.
 * Aplica el principio de responsabilidad única (cada servicio solo maneja la lógica de su juego).
 */
public interface GameService {

    /**
     * Inicializa el juego con los parámetros dados.
     *
     * @param params Mapa de parámetros (clave: nombre de parámetro, valor: su valor)
     */
    void initialize(Map<String, Object> params);

    /**
     * Resuelve automáticamente el puzzle.
     *
     * @return Solution con los movimientos y resultado
     */
    Solution solve();

    /**
     * Ejecuta un movimiento manual en modo interactivo.
     *
     * @param move Movimiento a ejecutar
     * @return verdadero si el movimiento fue válido
     */
    boolean playManual(Move move);

    /**
     * Opcional: Obtiene el estado actual representado como lista de posiciones ocupadas (solo si se necesita).
     */
    List<Position> getCurrentState();
}
