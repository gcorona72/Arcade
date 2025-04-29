package org.example.arcade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "arcade_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType type;

    @Convert(converter = ParamsConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> params;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false)
    private int moves;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    protected Result() { }

    public Result(GameType type,
                  Map<String, Object> params,
                  boolean success,
                  int moves,
                  LocalDateTime timestamp) {
        this.type = Objects.requireNonNull(type);
        this.params = Objects.requireNonNull(params);
        this.success = success;
        this.moves = moves;
        this.timestamp = Objects.requireNonNull(timestamp);
    }

    public Long getId() { return id; }
    public GameType getType() { return type; }
    public Map<String, Object> getParams() { return params; }
    public boolean isSuccess() { return success; }
    public int getMoves() { return moves; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", type=" + type +
                ", params=" + params +
                ", success=" + success +
                ", moves=" + moves +
                ", timestamp=" + timestamp +
                '}';
    }
}