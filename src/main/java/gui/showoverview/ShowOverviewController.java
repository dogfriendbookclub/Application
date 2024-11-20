package gui.showoverview;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import edu.metrostate.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowOverviewController implements Initializable {
    private APIclient apIclient = new APIclient();

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchBar;

    @FXML
    private MenuItem seasonSelector;

    @FXML
    private MenuButton seasonButton;

    @FXML
    private MenuItem episodeSelector;

    @FXML
    private MenuButton episodeButton;

     @FXML
    void loadHomePage(ActionEvent event) throws IOException {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));

         AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadSeasonPage(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seasonoverview/SeasonOverview.fxml"));


        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadEpisodePage(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/episodeoverview/EpisodeOverview.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    void loadSearchPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int showId = 1396; // hard coded test value for now
        try {
            Show show = apIclient.fetchShowData(showId);
            seasonButton.getItems().clear();

            for (Season season : show.getSeasons()) {
                MenuItem seasonItem = new MenuItem("Season " + season.getSeasonNumber());

                seasonItem.setOnAction(event -> {
                    seasonButton.setText(seasonItem.getText());
                    System.out.println("Selected " + seasonItem.getText());

                    episodeButton.getItems().clear();
                    episodeButton.setText("Select Episode");

                    for (Episode episode : season.getEpisodes()) {
                        MenuItem episodeItem = new MenuItem("Episode " + episode.getEpisodeNum());
                        System.out.println("Episode " + episode.getEpisodeNum() + " added to selector");

                        episodeItem.setOnAction(event2 -> {
                            episodeButton.setText(episodeItem.getText());
                            System.out.println("Selected " + episodeItem.getText());
                        });

                        episodeButton.getItems().add(episodeItem);
                    }
                });

                seasonButton.getItems().add(seasonItem);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch data", e);
        }
    }
}
