package javafx.src;

import java.io.IOException;
import java.security.Key;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;

import com.fasterxml.jackson.core.JsonParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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


public class SearchPageController implements Initializable{

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

    @FXML
    private TextField searchBar;

    private APIclient apIclient = new APIclient();

    @FXML
    private Label searchResult;

    public static Label static_label;

   @FXML
    private TextField txtfield;

    @FXML
    void loadSearchPage(ActionEvent event) throws IOException {

        static_label.setText(txtfield.getText());

//        listView.getItems().clear();
//        listView.getItems().addAll(searchList(searchBar.getText()));

//        searchResult = searchBar.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void initialize() {

        searchBar.setOnKeyPressed( e -> {
            try {
                List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
                for (ShowPreview result : testResults) {
                    System.out.println(result.toString());
                }
            } catch (Exception err) {
                System.out.println("Search API Test Error");
                err.printStackTrace();
            }
        } );


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_label = searchResult;
    }


}
