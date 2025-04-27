package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel para el juego de las N Reinas.
 */
public class QueenPanel extends JPanel {

    private final GameController controller;
    private final JTextField nField;
    private final JButton solveButton;
    private final JTextArea outputArea;

    public QueenPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Valor de N:"));
        nField = new JTextField("8", 5);
        inputPanel.add(nField);
        solveButton = new JButton("Resolver");
        inputPanel.add(solveButton);
        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        solveButton.addActionListener(e -> onSolve());
    }

    private void onSolve() {
        int N = Integer.parseInt(nField.getText().trim());
        Map<String, Object> params = new HashMap<>();
        params.put("N", N);

        Solution sol = controller.startAndSolve(GameType.REINAS, params);
        outputArea.setText("Resuelto: " + sol.isSolved() +
                "\\nMovimientos: " + sol.getMoves().size() +
                "\\nTiempo: " + sol.getDuration().toMillis() + " ms");
    }
}
