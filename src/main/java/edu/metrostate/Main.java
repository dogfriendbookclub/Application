package edu.metrostate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");

        StackPane root = new StackPane();

        Button button = new Button();
        button.setText("Button");
        root.getChildren().add(button);

        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }
}