package javafx.src;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchPageController implements Initializable{

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_label = searchResult;
    }

}
