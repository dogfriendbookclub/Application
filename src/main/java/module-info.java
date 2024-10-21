module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.sql;

    // Open the edu.metrostate package for FXML reflection
    opens edu.metrostate to javafx.fxml;

    // Export the edu.metrostate package for access by other modules
    exports edu.metrostate;
}
