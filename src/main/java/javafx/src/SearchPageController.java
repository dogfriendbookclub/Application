package javafx.src;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Objects;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;


public class SearchPageController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchBar;

    private APIclient apIclient = new APIclient();

    @FXML
    void loadHomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void initialize() {

        searchBar.setOnKeyPressed( e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.ENTER) {
                try {
                    List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
                    for (ShowPreview result : testResults) {
                        System.out.println(result.toString());
                    }
                } catch (Exception err) {
                    System.out.println("Search API Test Error");
                    err.printStackTrace();
                }
            }
        } );


    }
}
