// src/main/java/org/example/arcade/facade/GameFacade.java
package org.example.arcade.facade;

import org.example.arcade.factory.GameFactory;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;
import org.example.arcade.service.GameService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Fachada que unifica la inicialización, ejecución y obtención de resultados de un juego.
 */
@Component
public class GameFacade {

    private final GameFactory gameFactory;

    public GameFacade(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public Solution startGame(GameType type, Map<String, Object> params) {
        GameService service = gameFactory.createService(type);
        service.initialize(params);
        return service.solve();
    }
}
