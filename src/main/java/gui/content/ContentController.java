package gui.content;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentController implements Initializable {

    public Button logoutButton;
    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private BorderPane homePage;

    @FXML
    private BorderPane searchPage;

    @FXML
    private Button showOverviewButton;
    private ContentListener listener;

    @FXML
    void loadShowOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadHomePage(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadSearchPage(ActionEvent event) throws IOException {
        //System.out.println("search bar function");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    public interface ContentListener {
        void onLogout();
        void onHomeButton();
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
        logoutButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onLogout();
            }
        });


        homeButton.setOnAction(actionEvent -> {
            if (listener != null) {
                homeButtonCheck();
            }
        });

        searchBar.setOnAction(actionEvent -> {
            if (listener != null) {
                searchBarCheck();
            }
        });

        searchPage.setVisible(false);
    }


    private void  homeButtonCheck(){
        searchPage.setVisible(false);
        homePage.setVisible(true);
    }

    private void  searchBarCheck(){
        homePage.setVisible(false);
        searchPage.setVisible(true);
    }

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }
}
