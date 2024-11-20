package gui.content;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import gui.homepage.HomePageController;
import gui.searchpage.SearchPageController;
import gui.showoverview.ShowOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {
    private APIclient apIclient = new APIclient();


    //WHAT NEEDS TO BE SENT TO MAIN FIND OUT !!!
    //interface stuff
    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;

    //end interface stuff

    @FXML
    private BorderPane homePage;

    @FXML
    private BorderPane searchPage;

    @FXML
    private BorderPane showOverview;

    @FXML
    private HomePageController homePageController;

    @FXML
    private SearchPageController searchPageController;

    @FXML
    private ShowOverviewController showOverviewController;

    private ContentListener listener;


    @FXML
    void loadShowOverviewPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);

    }

    public void loadHomePage(ActionEvent actionEvent) {
        homePage.setVisible(false);

    public void onHomeButton(ActionEvent actionEvent) {
        homePage.setVisible(true);
        searchPage.setVisible(false);
        showPage.setVisible(true);

    }
*/

    public interface ContentListener {
        void onHomeButton();
        void onLogout();
        void searchTermEntered();
        void loadShowOverviewPage();
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
        System.out.println("contentcontroller is am being initialized");

        logoutButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onLogout();
            }
        });

        homeButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onHomeButton();
            }
        });

        searchBar.setOnAction(actionEvent -> {
            if (listener != null) {
                searchTest();
            }
        });

    }

    //getters for MainController to use
    public BorderPane getHomePage(){
        return this.homePage;
    }

    public BorderPane getSearchPage(){
        return this.searchPage;
    }

    public BorderPane getShowOverview(){
        return this.showOverview;
    }

    public HomePageController getHomePageController(){
        return this.homePageController;
    }

    public SearchPageController getSearchPageController(){
        return this.searchPageController;
    }

    public ShowOverviewController getShowOverviewController(){
        return this.showOverviewController;
    }

    //this is we at search control would go
    private void searchTest(){
        searchPageController.novaLuna(searchBar.getText());
        listener.searchTermEntered();

    }


    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }


}//end class
