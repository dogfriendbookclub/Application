package gui.episodeoverview;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.metrostate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class EpisodeOverviewController implements Initializable {

    private APIclient apIclient = new APIclient();

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
    private Label episodeNum;

    @FXML
    private ListView creatorList;

    @FXML
    private ListView castList;

    @FXML
    private Label episodeTitle;

    @FXML
    private TextArea synopsis;

    @FXML
    void loadSeasonPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seasonoverview/SeasonOverview.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadEpisodePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/episodeoverview/EpisodeOverview.fxml"));


        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    void loadSearchPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        // returns a list of results (to the console atm) for the search query with the text from the search bar
        try {
            List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
            for (ShowPreview result : testResults) {
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Search API Test Error");
            e.printStackTrace();
        }

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }
    private Show show;

    public void setShow(Show show) {
        this.show = show;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    private Season season;
    private Episode episode;

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

    }

    public void populatecreators(Show show) {
        List<Creator> creators = show.getCreators();  // Get the creators from the Show class
        // Create a list to store the creator names
        List<String> creatorNames = new ArrayList<>();
        for (Creator creator : creators) {
            creatorNames.add(creator.getName());  // Add creator name to the list
        }
        // Set the creator names to the ListView
        ObservableList<String> observableCreatorNames = FXCollections.observableArrayList(creatorNames);
        creatorList.setItems(observableCreatorNames);
    }
    public void loadEpisodeData(Show show, Season season, Episode episode) throws IOException {
        populatecreators(show);
        List<String> mainCast = apIclient.fetchCastForEpisode(season.getShowId(), season.getSeasonNumber(), episode.getEpisodeNum());
        ObservableList<String> observableMainCastList = FXCollections.observableArrayList(mainCast);
        castList.setItems(observableMainCastList);
        episodeNum.setText(Integer.toString(episode.getEpisodeNum()));
        synopsis.setText(episode.getSynopsis());
        episodeTitle.setText(episode.getEpisodeName());
    }
}
