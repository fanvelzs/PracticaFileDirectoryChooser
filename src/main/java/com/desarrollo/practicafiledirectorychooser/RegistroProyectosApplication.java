package com.desarrollo.practicafiledirectorychooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroProyectosApplication extends Application {
    private static Stage ventana;

    @Override
    public void start(Stage stage) throws IOException {
        ventana = stage;
        stage.setMinWidth(720);
        stage.setMinHeight(600);
        mostrarVista("menu-principal.fxml", "Menú principal");
        stage.show();
    }

    public static void mostrarVista(String archivoFxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                RegistroProyectosApplication.class.getResource(archivoFxml)
        );
        Scene scene = ventana.getScene();
        if (scene == null) {
            ventana.setScene(new Scene(loader.load(), 820, 620));
        } else {
            scene.setRoot(loader.load());
        }
        ventana.setTitle(titulo);
        ventana.centerOnScreen();
    }
}
