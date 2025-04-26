// src/main/java/org/example/arcade/model/Solution.java
package org.example.arcade.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Resultado de la resolución de un juego: lista de movimientos o posiciones,
 * éxito de la resolución y tiempo empleado.
 */
public class Solution {
    private final List<Move> moves;
    private final boolean solved;
    private final Duration duration;

    private final Instant timestamp;

    public Solution(List<Move> moves, boolean solved, Duration duration) {
        this.moves = Collections.unmodifiableList(
                Objects.requireNonNull(moves, "moves no puede ser null"));
        this.solved = solved;
        this.duration = Objects.requireNonNull(duration, "duration no puede ser null");
        this.timestamp = Instant.now();
    }

    public List<Move> getMoves() {
        return moves;
    }

    public boolean isSolved() {
        return solved;
    }

    public Duration getDuration() {
        return duration;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getMoveCount() {
        return moves.size();
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solved=" + solved +
                ", moves=" + moves.size() +
                ", duration=" + duration.toMillis() + "ms" +
                '}';
    }
}
