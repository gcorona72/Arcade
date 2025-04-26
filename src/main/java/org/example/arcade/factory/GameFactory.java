// src/main/java/org/example/arcade/factory/GameFactory.java
package org.example.arcade.factory;

import org.example.arcade.model.GameType;
import org.example.arcade.service.GameService;
import org.example.arcade.service.impl.HanoiService;
import org.example.arcade.service.impl.KnightService;
import org.example.arcade.service.impl.QueenService;

/**
 * Fábrica para instanciar servicios de juegos en función del tipo.
 * Aplica el patrón de diseño Factory Method (creacional).
 */
public class GameFactory {

    /**
     * Crea un servicio de juego basado en el tipo solicitado.
     *
     * @param type Tipo de juego.
     * @return Instancia correspondiente de GameService.
     */
    public GameService createService(GameType type) {
        switch (type) {
            case REINAS:
                return new QueenService();
            case KNIGHT:
                return new KnightService();
            case HANOI:
                return new HanoiService();
            default:
                throw new IllegalArgumentException("Tipo de juego no soportado: " + type);
        }
    }
}
