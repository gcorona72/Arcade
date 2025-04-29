// src/main/java/org/example/arcade/service/impl/KnightService.java
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
 * Lógica del problema Knight's Tour usando backtracking simple.
 */
public class KnightService implements GameService {

    private int size;
    private Position start;
    private boolean[][] visited;
    private List<Move> movesList;

    @Override
    public void initialize(Map<String, Object> params) {
        this.size = (int) params.getOrDefault("size", 8);
        this.start = (Position) params.getOrDefault("start", new Position(0, 0));
        this.visited = new boolean[size][size];
        this.movesList = new ArrayList<>();
    }

    @Override
    public Solution solve() {
        long startTime = System.currentTimeMillis();
        boolean solved = tour(start.getX(), start.getY(), 1);
        long duration = System.currentTimeMillis() - startTime;
        return new Solution(new ArrayList<>(movesList), solved, Duration.ofMillis(duration));
    }

    private boolean tour(int x, int y, int step) {
        visited[x][y] = true;
        movesList.add(new Move(new Position(x, y), new Position(x, y)));
        if (step == size * size) return true;

        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (isValid(nx, ny)) {
                if (tour(nx, ny, step + 1)) return true;
            }
        }
        // backtrack
        visited[x][y] = false;
        movesList.remove(movesList.size() - 1);
        return false;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < size && y < size && !visited[x][y];
    }

    @Override
    public boolean playManual(Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        boolean valid = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
        if (valid && !visited[to.getX()][to.getY()]) {
            visited[to.getX()][to.getY()] = true;
            movesList.add(move);
        }
        return valid;
    }

    @Override
    public List<Position> getCurrentState() {
        List<Position> state = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (visited[x][y]) state.add(new Position(x, y));
            }
        }
        return state;
    }
}
