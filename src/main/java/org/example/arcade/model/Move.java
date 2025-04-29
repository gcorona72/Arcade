// src/main/java/org/example/arcade/model/Move.java
package org.example.arcade.model;

import java.util.Objects;

/**
 * Representa un movimiento desde una posición origen hasta otra destino.
 */
public final class Move {
    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = Objects.requireNonNull(from, "from no puede ser null");
        this.to = Objects.requireNonNull(to, "to no puede ser null");
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return from.equals(move.from) && to.equals(move.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Move{" + from + "->" + to + "}";
    }
}
