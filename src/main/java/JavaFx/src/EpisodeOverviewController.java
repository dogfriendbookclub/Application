package JavaFx.src;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EpisodeOverviewController {

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField searchBar1;

    @FXML
    private MenuItem seasonSelector;
    
    @FXML 
    private MenuItem episodeSelector;

    @FXML
    void loadHomePage(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadSeasonPage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SeasonOverview.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadEpisodePage(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EpisodeOverview.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
