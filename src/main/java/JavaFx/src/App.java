import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       

        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);



        primaryStage.setTitle("Rotten Potatoes!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}