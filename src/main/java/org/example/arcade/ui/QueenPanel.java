// src/main/java/org/example/arcade/ui/QueenPanel.java
package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class QueenPanel extends JPanel {

    private final GameController controller;
    private final Consumer<Void> onBack;
    private final JTextField nField = new JTextField("8", 5);
    private final JButton solveButton = new JButton("Resolver");
    private final JTextArea outputArea = new JTextArea();

    public QueenPanel(GameController controller, Runnable backAction) {
        this.controller = controller;
        this.onBack = v -> backAction.run();
        setLayout(new BorderLayout());
        add(createTopBar(), BorderLayout.NORTH);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        solveButton.addActionListener(e -> onSolve());
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton back = new JButton("◀ Volver");
        back.addActionListener(e -> onBack.accept(null));
        topBar.add(back);
        topBar.add(new JLabel("Valor de N:"));
        topBar.add(nField);
        topBar.add(solveButton);
        return topBar;
    }

    private void onSolve() {
        try {
            int N = Integer.parseInt(nField.getText().trim());
            Map<String, Object> params = new HashMap<>();
            params.put("N", N);

            Solution sol = controller.startAndSolve(GameType.REINAS, params);
            outputArea.setText(
                    "Resuelto: " + sol.isSolved() +
                            "\nMovimientos: " + sol.getMoveCount() +
                            "\nTiempo: " + sol.getDuration().toMillis() + " ms"
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduce un número válido para N", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
