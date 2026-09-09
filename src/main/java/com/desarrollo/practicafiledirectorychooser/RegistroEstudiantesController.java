package com.desarrollo.practicafiledirectorychooser;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RegistroEstudiantesController {
    @FXML private TextField cifField;
    @FXML private TextField nombresField;
    @FXML private TextField apellidosField;
    @FXML private TextField carreraField;
    @FXML private TextField correoField;

    @FXML
    private void guardarEstudiante() {
        if (cifField.getText().isBlank() || nombresField.getText().isBlank()
                || apellidosField.getText().isBlank() || carreraField.getText().isBlank()
                || correoField.getText().isBlank()) {
            alerta(Alert.AlertType.WARNING, "Complete todos los campos.");
            return;
        }

        if (!cifField.getText().trim().matches("\\d+")) {
            alerta(Alert.AlertType.WARNING, "El número CIF debe contener únicamente números.");
            cifField.requestFocus();
            return;
        }

        if (!correoField.getText().trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            alerta(Alert.AlertType.WARNING, "Escriba un correo electrónico válido.");
            correoField.requestFocus();
            return;
        }

        String fila = String.join(",",
                csv(cifField.getText()), csv(nombresField.getText()),
                csv(apellidosField.getText()), csv(carreraField.getText()),
                csv(correoField.getText())) + System.lineSeparator();

        try {
            Files.writeString(Path.of("estudiantes.csv"), fila, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            alerta(Alert.AlertType.INFORMATION, "Estudiante guardado correctamente.");
            limpiar();
        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "No se pudo guardar: " + e.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        cifField.clear();
        nombresField.clear();
        apellidosField.clear();
        carreraField.clear();
        correoField.clear();
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
