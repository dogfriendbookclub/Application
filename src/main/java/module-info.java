module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires java.sql;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
}