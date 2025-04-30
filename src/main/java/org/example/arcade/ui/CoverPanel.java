// src/main/java/org/example/arcade/ui/CoverPanel.java
package org.example.arcade.ui;

import org.example.arcade.model.GameType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de portada estilo arcade: cada “cuadrícula” es un JPanel con icono y etiqueta.
 * Al hacer hover, pinta un marco blanco alrededor del panel, y al click dispara la acción.
 */
public class CoverPanel extends JPanel {

    public CoverPanel(ActionListener onSelect) {
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;

        add(createTile("N Reinas", "/images/reinas.png", GameType.REINAS.name(), onSelect), gbc);

        gbc.gridx = 1;
        add(createTile("Knight’s Tour", "/images/knight.png", GameType.KNIGHT.name(), onSelect), gbc);

        gbc.gridx = 2;
        add(createTile("Torres de Hanói", "/images/hanoi.png", GameType.HANOI.name(), onSelect), gbc);

        // Historial en fila inferior, centro
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(createTile("Historial", "/images/history.png", "HISTORY", onSelect), gbc);
    }

    private JPanel createTile(String label, String iconPath, String actionCommand, ActionListener listener) {
        // Panel contenedor
        JPanel tile = new JPanel(new BorderLayout());
        tile.setPreferredSize(new Dimension(180, 200));
        tile.setBackground(new Color(60, 60, 100));
        tile.setBorder(new LineBorder(new Color(60, 60, 100), 4));
        tile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Icono
        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            // Escalar icono si es necesario:
            Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } catch (Exception ignored) {}
        tile.add(iconLabel, BorderLayout.CENTER);

        // Texto
        JLabel textLabel = new JLabel(label);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("ArcadeClassic", Font.PLAIN, 14));
        tile.add(textLabel, BorderLayout.SOUTH);

        // Acción de click
        tile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(
                        new java.awt.event.ActionEvent(tile, ActionEvent.ACTION_PERFORMED, actionCommand));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                tile.setBorder(new LineBorder(Color.WHITE, 4));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tile.setBorder(new LineBorder(new Color(60, 60, 100), 4));
            }
        });

        return tile;
    }
}
