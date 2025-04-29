// src/main/java/org/example/arcade/ArcadeApplication.java
package org.example.arcade;

import org.example.arcade.controller.GameController;
import org.example.arcade.ui.MainWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

@SpringBootApplication
public class ArcadeApplication {

    public static void main(String[] args) throws Exception {
        // 1. Look & Feel Nimbus
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        // 2. Cargar fuente retro desde resources/fonts/ArcadeClassic.ttf
        try (InputStream is = ArcadeApplication.class.getResourceAsStream("/fonts/ArcadeClassic.ttf")) {
            if (is != null) {
                Font arcadeFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
                UIManager.put("Label.font", arcadeFont);
                UIManager.put("Button.font", arcadeFont);
            } else {
                System.err.println("No se encontró la fuente ArcadeClassic.ttf en /fonts/");
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        // 3. Ajustar colores globales de Nimbus
        UIManager.put("control", new Color(50, 50, 70));
        UIManager.put("nimbusBase", new Color(30, 30, 60));
        UIManager.put("nimbusBlueGrey", new Color(80, 80, 100));
        UIManager.put("nimbusFocus", new Color(200, 180, 60));

        // 4. Arrancar Spring
        ConfigurableApplicationContext ctx = SpringApplication.run(ArcadeApplication.class, args);

        // 5. Mostrar UI
        GameController controller = ctx.getBean(GameController.class);
        SwingUtilities.invokeLater(() -> new MainWindow(controller).setVisible(true));

        // 6. Mantener vivo el proceso
        Thread.currentThread().join();
    }
}
