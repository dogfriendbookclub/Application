package javafx.src;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class SearchbarController {

    private APIclient apIclient = new APIclient();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchBar;

    @FXML
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

}
