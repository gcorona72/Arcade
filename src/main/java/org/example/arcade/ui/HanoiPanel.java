package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel para las Torres de Hanói.
 */
public class HanoiPanel extends JPanel {

    private final GameController controller;
    private final JTextField disksField;
    private final JButton solveButton;
    private final JTextArea outputArea;

    public HanoiPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel input = new JPanel();
        input.add(new JLabel("Discos:"));
        disksField = new JTextField("3", 5);
        input.add(disksField);

        solveButton = new JButton("Resolver");
        input.add(solveButton);
        add(input, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        solveButton.addActionListener(e -> onSolve());
    }

    private void onSolve() {
        int disks = Integer.parseInt(disksField.getText().trim());
        Map<String, Object> params = new HashMap<>();
        params.put("disks", disks);

        Solution sol = controller.startAndSolve(GameType.HANOI, params);
        outputArea.setText("Resuelto: " + sol.isSolved() +
                "\\nMovimientos: " + sol.getMoves().size() +
                "\\nTiempo: " + sol.getDuration().toMillis() + " ms");
    }
}
