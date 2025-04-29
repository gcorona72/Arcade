// src/main/java/org/example/arcade/controller/GameController.java
package org.example.arcade.controller;

import org.example.arcade.facade.GameFacade;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Move;
import org.example.arcade.model.Result;
import org.example.arcade.model.Solution;
import org.example.arcade.repository.ResultRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador principal de la aplicación arcade.
 */
@Component
public class GameController {

    private final GameFacade gameFacade;
    private final ResultRepository resultRepository;

    public GameController(GameFacade gameFacade,
                          ResultRepository resultRepository) {
        this.gameFacade = gameFacade;
        this.resultRepository = resultRepository;
    }

    public Solution startAndSolve(GameType type, Map<String, Object> params) {
        Solution sol = gameFacade.startGame(type, params);
        Result r = new Result(type, new HashMap<>(params),
                sol.isSolved(),
                sol.getMoveCount(),
                LocalDateTime.now());
        resultRepository.save(r);
        return sol;
    }

    public boolean handleManualMove(GameType type,
                                    Map<String, Object> params,
                                    Move manualMove) {
        // Si necesitas modo manual:
        return gameFacade
                .getClass()    // workaround: factory is private in facade
                .cast(gameFacade)
                .startGame(type, params) // Init+solve, but manual not used here
                .isSolved();
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
