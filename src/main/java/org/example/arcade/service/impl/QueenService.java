// src/main/java/org/example/arcade/service/impl/QueenService.java
package org.example.arcade.service.impl;

import org.example.arcade.model.Move;
import org.example.arcade.model.Position;
import org.example.arcade.model.Solution;
import org.example.arcade.service.GameService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Lógica del problema de las N reinas usando backtracking.
 */
public class QueenService implements GameService {

    private int N;
    private int[] queens;            // índice = fila, valor = columna
    private List<Move> solutionMoves; // no aplica movimientos en Queen, pero podemos registrar posiciones

    @Override
    public void initialize(Map<String, Object> params) {
        this.N = (int) params.getOrDefault("N", 8);
        this.queens = new int[N];
        this.solutionMoves = new ArrayList<>();
    }

    @Override
    public Solution solve() {
        long start = System.currentTimeMillis();
        boolean solved = placeQueen(0);
        long duration = System.currentTimeMillis() - start;
        // construir lista de posiciones finales
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            Position from = new Position(row, queens[row]);
            moves.add(new Move(from, from));
        }
        return new Solution(moves, solved, Duration.ofMillis(duration));
    }

    private boolean placeQueen(int row) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                queens[row] = col;
                if (placeQueen(row + 1)) return true;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int r = 0; r < row; r++) {
            int c = queens[r];
            if (c == col || Math.abs(r - row) == Math.abs(c - col)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean playManual(Move move) {
        // No implementado: modo manual no aplicable a este servicio
        return false;
    }

    @Override
    public List<Position> getCurrentState() {
        List<Position> state = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            state.add(new Position(row, queens[row]));
        }
        return state;
    }
}
