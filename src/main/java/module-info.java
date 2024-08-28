module Application {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
}