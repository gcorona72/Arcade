// src/main/java/org/example/arcade/ArcadeApplication.java
package org.example.arcade;

import org.example.arcade.controller.GameController;
import org.example.arcade.ui.MainWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class ArcadeApplication {

    public static void main(String[] args) throws InterruptedException {
        // Arranca el contexto de Spring
        ConfigurableApplicationContext ctx = SpringApplication.run(ArcadeApplication.class, args);

        // Recupera el controlador bean
        GameController controller = ctx.getBean(GameController.class);

        // Lanza la UI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(controller);
            window.setVisible(true);
        });

        // Evita que el proceso JVM termine inmediatamente
        Thread.currentThread().join();
    }
}
