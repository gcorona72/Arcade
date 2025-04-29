// src/main/java/org/example/arcade/service/impl/HanoiService.java
package org.example.arcade.service.impl;

import org.example.arcade.model.Move;
import org.example.arcade.model.Position;
import org.example.arcade.model.Solution;
import org.example.arcade.service.GameService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Lógica del problema Torres de Hanói (recursivo).
 */
public class HanoiService implements GameService {

    private int disks;
    private final Stack<Position> source = new Stack<>();
    private final Stack<Position> auxiliary = new Stack<>();
    private final Stack<Position> target = new Stack<>();
    private final List<Move> moves = new ArrayList<>();

    @Override
    public void initialize(Map<String, Object> params) {
        this.disks = (int) params.getOrDefault("disks", 3);
        source.clear(); auxiliary.clear(); target.clear(); moves.clear();
        for (int i = disks; i >= 1; i--) {
            source.push(new Position(i, 0)); // valor Y=0 indica poste
        }
    }

    @Override
    public Solution solve() {
        long start = System.currentTimeMillis();
        moveDisks(disks, source, target, auxiliary);
        long duration = System.currentTimeMillis() - start;
        return new Solution(new ArrayList<>(moves), true, Duration.ofMillis(duration));
    }

    private void moveDisks(int n, Stack<Position> from, Stack<Position> to, Stack<Position> aux) {
        if (n == 0) return;
        moveDisks(n - 1, from, aux, to);
        Position disk = from.pop();
        to.push(disk);
        moves.add(new Move(disk, new Position(disk.getX(), to == target ? 2 : (to == auxiliary ? 1 : 0))));
        moveDisks(n - 1, aux, to, from);
    }

    @Override
    public boolean playManual(Move move) {
        // Podrías implementar validación de reglas si añades modo manual
        return false;
    }

    @Override
    public List<Position> getCurrentState() {
        // Podríamos devolver el estado de las tres torres
        return new ArrayList<>(source); // simplificación
    }
}
