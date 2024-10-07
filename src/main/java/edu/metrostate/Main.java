package edu.metrostate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Rotten Potatoes!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    /*


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
    // public static void main(String[] args){
    //     launch(args);
    // }
*/
}  //end class

