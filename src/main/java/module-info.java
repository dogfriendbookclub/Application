module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.sql;


    opens gui to javafx.fxml;
    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
   // exports gui.src;
    exports gui;
    exports gui.content;
    opens gui.content to javafx.fxml;
    exports gui.episodeoverview;
    opens gui.episodeoverview to javafx.fxml;
    opens gui.homepage to javafx.fxml;
    exports gui.homepage;
    exports gui.searchpage;
    opens gui.searchpage to javafx.fxml;
    exports gui.seasonoverview;
    opens gui.seasonoverview to javafx.fxml;
    exports gui.showoverview;
    opens gui.showoverview to javafx.fxml;


    exports gui.login;
    opens gui.login to javafx.fxml;
}