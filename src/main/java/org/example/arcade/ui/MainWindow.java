// src/main/java/org/example/arcade/ui/MainWindow.java
package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    private final GameController controller;

    public MainWindow(GameController controller) {
        this.controller = controller;
        setTitle("Arcade de Puzzles");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        showCoverMenu();
    }

    void showCoverMenu() {
        getContentPane().removeAll();
        getContentPane().add(new CoverPanel(this::handleSelection));
        revalidate();
        repaint();
    }

    private void handleSelection(ActionEvent e) {
        getContentPane().removeAll();
        GameType type = GameType.valueOf(e.getActionCommand());
        switch (type) {
            case REINAS  -> getContentPane().add(new QueenPanel(controller, this::showCoverMenu));
            case KNIGHT  -> getContentPane().add(new KnightPanel(controller, this::showCoverMenu));
            case HANOI   -> getContentPane().add(new HanoiPanel(controller, this::showCoverMenu));
        }
        revalidate();
        repaint();
    }
}
