// src/main/java/org/example/arcade/controller/GameController.java
package org.example.arcade.controller;

import org.example.arcade.model.Move;
import org.example.arcade.model.Result;
import org.example.arcade.model.Solution;
import org.example.arcade.model.GameType;
import org.example.arcade.repository.ResultRepository;
import org.example.arcade.service.GameService;
import org.example.arcade.factory.GameFactory;
import org.example.arcade.facade.GameFacade;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador principal de la aplicación arcade.
 * Se encarga de coordinar la creación de servicios, ejecución de la lógica de juego y persistencia.
 */
public class GameController {

    private final GameFactory gameFactory;
    private final ResultRepository resultRepository;
    private final GameFacade gameFacade;

    public GameController(GameFactory gameFactory,
                          ResultRepository resultRepository,
                          GameFacade gameFacade) {
        this.gameFactory = gameFactory;
        this.resultRepository = resultRepository;
        this.gameFacade = gameFacade;
    }

    /**
     * Inicia y resuelve un juego automáticamente.
     *
     * @param type   Tipo de juego a ejecutar.
     * @param params Parámetros específicos del juego.
     * @return Solución obtenida.
     */
    public Solution startAndSolve(GameType type, Map<String, Object> params) {
        // Usamos la fachada para unificar lógica + persistencia
        Solution solution = gameFacade.startGame(type, params);
        // Registrar resultado
        Map<String, Object> recordParams = new HashMap<>(params);
        Result result = new Result(type, recordParams, solution.isSolved(),
                solution.getMoveCount(), LocalDateTime.now());
        resultRepository.save(result);
        return solution;
    }

    /**
     * Procesa un movimiento manual en modo interactivo.
     *
     * @param type      Tipo de juego.
     * @param params    Parámetros del juego (inicialización).
     * @param manualMove Movimiento a aplicar.
     * @return true si válido.
     */
    public boolean handleManualMove(GameType type,
                                    Map<String, Object> params,
                                    Move manualMove) {
        GameService service = gameFactory.createService(type);
        service.initialize(params);
        return service.playManual(manualMove);
    }

    /**
     * Obtiene el historial completo de resultados guardados.
     *
     * @return Lista de resultados.
     */
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
