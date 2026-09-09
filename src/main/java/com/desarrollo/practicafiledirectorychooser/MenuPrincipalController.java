package com.desarrollo.practicafiledirectorychooser;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuPrincipalController {
    @FXML
    private void abrirRegistroProyectos() throws IOException {
        RegistroProyectosApplication.mostrarVista(
                "registro-proyectos.fxml", "Registro de proyectos"
        );
    }

    @FXML
    private void abrirRegistroEstudiantes() throws IOException {
        RegistroProyectosApplication.mostrarVista(
                "registro-estudiantes.fxml", "Registro de estudiantes"
        );
    }
}
