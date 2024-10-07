module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires com.fasterxml.jackson.databind;

    opens javafx.src to javafx.fxml;  // Ensure this is opened
    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
    exports javafx.src;
}