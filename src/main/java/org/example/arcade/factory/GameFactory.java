// src/main/java/org/example/arcade/factory/GameFactory.java
package org.example.arcade.factory;

import org.example.arcade.model.GameType;
import org.example.arcade.service.GameService;
import org.example.arcade.service.impl.HanoiService;
import org.example.arcade.service.impl.KnightService;
import org.example.arcade.service.impl.QueenService;
import org.springframework.stereotype.Component;

/**
 * Fábrica para instanciar servicios de juegos en función del tipo.
 * Aplica el patrón Factory Method.
 */
@Component
public class GameFactory {

    public GameService createService(GameType type) {
        return switch (type) {
            case REINAS -> new QueenService();
            case KNIGHT -> new KnightService();
            case HANOI -> new HanoiService();
        };
    }
}
