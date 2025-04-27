package org.example.arcade.ui;

import org.example.arcade.controller.GameController;
import org.example.arcade.model.Result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Dialog para mostrar el historial de partidas guardadas.
 */
public class ResultHistoryDialog extends JDialog {

    public ResultHistoryDialog(Frame owner, GameController controller) {
        super(owner, "Historial de Partidas", true);
        setSize(600, 400);
        setLocationRelativeTo(owner);

        List<Result> results = controller.getAllResults();

        String[] cols = {"ID", "Juego", "Params", "Éxito", "Movimientos", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for (Result r : results) {
            model.addRow(new Object[]{
                    r.getId(), r.getType(), r.getParams(),
                    r.isSuccess(), r.getMoves(), r.getTimestamp()
            });
        }
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
