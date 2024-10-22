package javafx.src;

import java.io.IOException;
import java.security.Key;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import edu.metrostate.APIclient;
import edu.metrostate.Show;
import edu.metrostate.ShowPreview;

import com.fasterxml.jackson.core.JsonParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;


public class SearchPageController {

    DataSingleton data = DataSingleton.getInstance();

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void loadHomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    private APIclient apIclient = new APIclient();

    @FXML
    private TextField searchBar;

    @FXML
    private Label searchResult;

    @FXML
    private Label showTitle1;

    @FXML
    void loadSearchPage(ActionEvent event) throws IOException {

        data.setSearchString(searchBar.getText()); // Storing searchString data from current page

        // ----------------- Loading the new search page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);


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
        // ----------------- Extras
    }

    public void initialize() {

        searchResult.setText("You entered: \"" + data.getSearchString() + "\"");

        searchBar.setOnKeyPressed( e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.ENTER) {
                try {
                    List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
                    for (ShowPreview result : testResults) {
                        System.out.println(result.toString());
                    }

                    System.out.println("Movie Title: " + testResults.get(1).getTitle()); // Prints out the second object's title with index 1
                    showTitle1.setText(testResults.get(1).getTitle()); // Trying to set showTitle1 as the second object's title. NOT WORKING*
                    // Getting showTitle1 is NULL error.

                } catch (Exception err) {
                    System.out.println("Search API Test Error");
                    err.printStackTrace();
                }
            }
        } );



    }




}
