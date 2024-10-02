module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;

    opens edu.metrostate.gui to javafx.fxml;
    exports edu.metrostate.gui;
    exports edu.metrostate.gui.studentlist;
    opens edu.metrostate.gui.studentlist to javafx.fxml;
    exports edu.metrostate.gui.login;
    opens edu.metrostate.gui.login to javafx.fxml;
    exports edu.metrostate.gui.content;
    opens edu.metrostate.gui.content to javafx.fxml;
    exports edu.metrostate.gui.image;
    opens edu.metrostate.gui.image to javafx.fxml;
}