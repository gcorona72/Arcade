// src/main/java/org/example/arcade/facade/GameFacade.java
package org.example.arcade.facade;

import org.example.arcade.factory.GameFactory;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;
import org.example.arcade.service.GameService;

import java.util.Map;

/**
 * Fachada que unifica la inicialización, ejecución y obtención de resultados de un juego.
 * Aplica el patrón Facade (estructural).
 */
public class GameFacade {

    private final GameFactory gameFactory;

    public GameFacade(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    /**
     * Inicia el servicio de juego correspondiente, lo resuelve y devuelve la solución.
     *
     * @param type   Tipo de juego.
     * @param params Parámetros de inicialización.
     * @return Solution con movimientos y resultado.
     */
    public Solution startGame(GameType type, Map<String, Object> params) {
        GameService service = gameFactory.createService(type);
        service.initialize(params);
        return service.solve();
    }
}
