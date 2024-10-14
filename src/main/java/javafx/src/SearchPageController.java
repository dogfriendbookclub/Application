package javafx.src;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchPageController {

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchBar;

    @FXML
    void loadHomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

}
