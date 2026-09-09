package com.desarrollo.practicafiledirectorychooser;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RegistroProyectosController {
    @FXML private TextField nombreField;
    @FXML private TextField responsableField;
    @FXML private TextArea descripcionArea;
    @FXML private TextField archivoField;
    @FXML private TextField directorioField;

    @FXML
    private void seleccionarArchivo() {
        File archivo = new FileChooser().showOpenDialog(nombreField.getScene().getWindow());
        if (archivo != null) archivoField.setText(archivo.getAbsolutePath());
    }

    @FXML
    private void seleccionarDirectorio() {
        File directorio = new DirectoryChooser().showDialog(nombreField.getScene().getWindow());
        if (directorio != null) directorioField.setText(directorio.getAbsolutePath());
    }

    @FXML
    private void guardarProyecto() {
        if (nombreField.getText().isBlank() || responsableField.getText().isBlank()
                || descripcionArea.getText().isBlank() || archivoField.getText().isBlank()
                || directorioField.getText().isBlank()) {
            alerta(Alert.AlertType.WARNING, "Complete todos los campos.");
            return;
        }

        String fila = String.join(",",
                csv(nombreField.getText()), csv(responsableField.getText()),
                csv(descripcionArea.getText()), csv(archivoField.getText()),
                csv(directorioField.getText())) + System.lineSeparator();

        try {
            Files.writeString(Path.of("proyectos.csv"), fila, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            alerta(Alert.AlertType.INFORMATION, "Proyecto guardado correctamente.");
            limpiar();
        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "No se pudo guardar: " + e.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        nombreField.clear();
        responsableField.clear();
        descripcionArea.clear();
        archivoField.clear();
        directorioField.clear();
    }

    @FXML
    private void volverMenu() throws IOException {
        RegistroProyectosApplication.mostrarVista("menu-principal.fxml", "Menú principal");
    }

    private String csv(String texto) {
        return "\"" + texto.trim().replace("\"", "\"\"") + "\"";
    }

    private void alerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
