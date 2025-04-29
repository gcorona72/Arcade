// src/main/java/org/example/arcade/ui/KnightPanel.java
package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Position;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class KnightPanel extends JPanel {

    private final GameController controller;
    private final JTextField sizeField = new JTextField("8", 5);
    private final JTextField xField = new JTextField("0", 3);
    private final JTextField yField = new JTextField("0", 3);
    private final JButton solveButton = new JButton("Resolver");
    private final JTextArea outputArea = new JTextArea();

    public KnightPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Tamaño:")); top.add(sizeField);
        top.add(new JLabel("Start X:")); top.add(xField);
        top.add(new JLabel("Start Y:")); top.add(yField);
        top.add(solveButton);
        add(top, BorderLayout.NORTH);

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        solveButton.addActionListener(e -> onSolve());
    }

    private void onSolve() {
        try {
            int size = Integer.parseInt(sizeField.getText().trim());
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());

            Map<String, Object> params = new HashMap<>();
            params.put("size", size);
            params.put("start", new Position(x, y));

            Solution sol = controller.startAndSolve(GameType.KNIGHT, params);
            outputArea.setText(
                    "Resuelto: " + sol.isSolved() +
                            "\nMovimientos: " + sol.getMoveCount() +
                            "\nTiempo: " + sol.getDuration().toMillis() + " ms"
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valores inválidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
