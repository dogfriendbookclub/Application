package gui.episodeoverview;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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

        // returns a list of results (to the console atm) for the search query with the text from the search bar
        try {
            List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
            for ( ShowPreview result : testResults) {
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Search API Test Error");
            e.printStackTrace();
        }

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

    }
}
