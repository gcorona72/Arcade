// src/main/java/org/example/arcade/ui/MainWindow.java
package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.GameType;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final GameController controller;

    public MainWindow(GameController controller) {
        this.controller = controller;
        setTitle("Arcade de Puzzles");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initMenu();
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Juegos");

        JMenuItem reinasItem = new JMenuItem("N Reinas");
        reinasItem.addActionListener(e -> openPanel(GameType.REINAS));

        JMenuItem knightItem = new JMenuItem("Knight’s Tour");
        knightItem.addActionListener(e -> openPanel(GameType.KNIGHT));

        JMenuItem hanoiItem = new JMenuItem("Torres de Hanói");
        hanoiItem.addActionListener(e -> openPanel(GameType.HANOI));

        JMenuItem historyItem = new JMenuItem("Historial");
        historyItem.addActionListener(e -> new ResultHistoryDialog(this, controller).setVisible(true));

        menu.add(reinasItem);
        menu.add(knightItem);
        menu.add(hanoiItem);
        menu.addSeparator();
        menu.add(historyItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void openPanel(GameType type) {
        getContentPane().removeAll();
        switch (type) {
            case REINAS  -> getContentPane().add(new QueenPanel(controller));
            case KNIGHT -> getContentPane().add(new KnightPanel(controller));
            case HANOI  -> getContentPane().add(new HanoiPanel(controller));
        }
        // Fuerza el layout y repintado
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
