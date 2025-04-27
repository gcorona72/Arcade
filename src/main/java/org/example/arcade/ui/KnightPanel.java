package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Position;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel para el Knight’s Tour.
 */
public class KnightPanel extends JPanel {

    private final GameController controller;
    private final JTextField sizeField;
    private final JTextField xField, yField;
    private final JButton solveButton;
    private final JTextArea outputArea;

    public KnightPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel input = new JPanel();
        input.add(new JLabel("Tamaño:"));
        sizeField = new JTextField("8", 5);
        input.add(sizeField);
        input.add(new JLabel("Start X:"));
        xField = new JTextField("0", 3);
        input.add(xField);
        input.add(new JLabel("Start Y:"));
        yField = new JTextField("0", 3);
        input.add(yField);

        solveButton = new JButton("Resolver");
        input.add(solveButton);
        add(input, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        solveButton.addActionListener(e -> onSolve());
    }

    private void onSolve() {
        int size = Integer.parseInt(sizeField.getText().trim());
        int x = Integer.parseInt(xField.getText().trim());
        int y = Integer.parseInt(yField.getText().trim());

        Map<String, Object> params = new HashMap<>();
        params.put("size", size);
        params.put("start", new Position(x, y));

        Solution sol = controller.startAndSolve(GameType.KNIGHT, params);
        outputArea.setText("Resuelto: " + sol.isSolved() +
                "\\nMovimientos: " + sol.getMoves().size() +
                "\\nTiempo: " + sol.getDuration().toMillis() + " ms");
    }
}
