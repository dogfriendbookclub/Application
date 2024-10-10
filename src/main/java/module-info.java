module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.sql;

    opens javafx.src to javafx.fxml;
    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
    exports javafx.src;
}