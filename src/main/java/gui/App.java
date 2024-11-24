package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            System.out.println("initializing app.java");

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src/main/resources/fxml/homepage/HomePage.fxml")));
            Scene scene = new Scene(root);


            primaryStage.setTitle("app");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}