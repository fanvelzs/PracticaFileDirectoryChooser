module com.desarrollo.practicafiledirectorychooser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.desarrollo.practicafiledirectorychooser to javafx.fxml;
    exports com.desarrollo.practicafiledirectorychooser;
}
