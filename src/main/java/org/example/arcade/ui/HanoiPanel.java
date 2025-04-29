// src/main/java/org/example/arcade/ui/HanoiPanel.java
package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;
import org.example.arcade.model.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HanoiPanel extends JPanel {

    private final GameController controller;
    private final JTextField disksField = new JTextField("3", 5);
    private final JButton solveButton = new JButton("Resolver");
    private final JTextArea outputArea = new JTextArea();

    public HanoiPanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Discos:")); top.add(disksField);
        top.add(solveButton);
        add(top, BorderLayout.NORTH);

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        solveButton.addActionListener(e -> onSolve());
    }

    private void onSolve() {
        try {
            int d = Integer.parseInt(disksField.getText().trim());
            Map<String, Object> params = new HashMap<>();
            params.put("disks", d);

            Solution sol = controller.startAndSolve(GameType.HANOI, params);
            outputArea.setText(
                    "Resuelto: " + sol.isSolved() +
                            "\nMovimientos: " + sol.getMoveCount() +
                            "\nTiempo: " + sol.getDuration().toMillis() + " ms"
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduce un número válido de discos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
