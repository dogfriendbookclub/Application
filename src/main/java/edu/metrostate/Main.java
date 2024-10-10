package edu.metrostate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/content/Content.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Rotten Potatoes!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    /*stage.setTitle("Welcome to Rotton Potatoes");
        StackPane root = new StackPane();

        Button button = new Button();
        button.setText("Start");
        root.getChildren().add(button);

        stage.setScene(new Scene(root, 800, 800));
        stage.show(); */

        /*APIclient client = new APIclient();
        int showId = 1396; // Replace with the actual show ID
        int seasonNumberToPrint = 3; // Specify the season number you want to print

        try {
            Show show = client.fetchShowData(showId);
            System.out.println("Show: " + show.getTitle());
            System.out.println("Status: " + show.isStillRunning());

            for (Season season : show.getSeasons()) {
                if (season.getSeasonNumber() == seasonNumberToPrint) {
                    System.out.println("Season " + season.getSeasonNumber() + ": " + season.getAirdate());
                    System.out.println("Number of Episodes: " + season.getEpisodeCount());
                    for (Episode episode : season.getEpisodes()) {
                        System.out.println("  Episode " + episode.getEpisodeNum() + ": " + episode.getEpisodeName());
                        System.out.println("    Synopsis: " + episode.getSynopsis());
                        System.out.println("    Runtime: " + episode.getRuntime() + " minutes");
                        System.out.println("    Vote Count: " + episode.getEpisodeStars());
                    }
                    break; // Exit the loop once the desired season is found and printed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } */

    // public static void main(String[] args){
    //     launch(args);
    // }

}  //end class

