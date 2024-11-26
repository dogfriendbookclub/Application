package gui.content;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import gui.homepage.HomePageController;
import gui.searchpage.SearchPageController;
import gui.showoverview.ShowOverviewController;
import gui.userprofile.ProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {

    private APIclient apIclient = new APIclient();

    //WHAT NEEDS TO BE SENT TO MAIN FIND OUT !!!
    //interface stuff

    @FXML
    private Button profileButton;


    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;


    @FXML
    private BorderPane homePage;

    @FXML
    private BorderPane searchPage;

    @FXML
    private BorderPane showOverview;

    @FXML
    private BorderPane profile;

    @FXML
    private HomePageController homePageController;

    @FXML
    private SearchPageController searchPageController;

    @FXML
    private ShowOverviewController showOverviewController;

    @FXML
    private ProfileController profileController;





    private ContentListener listener;

/*
    /*
    @FXML
    void loadShowOverviewPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);

    }

    public void loadHomePage(ActionEvent actionEvent) {
        homePage.setVisible(false);
    }

    public void onHomeButton(ActionEvent actionEvent) {
        homePage.setVisible(true);
        searchPage.setVisible(false);
        showPage.setVisible(true);

    }
C:\Users\lemon\OneDrive\Documents\git_repositories\ics372_project_1_rep\Application\src\main\resources\fxml\showoverview\ShowOverview.fxml



*/

    public interface ContentListener {
        void onHomeButton();

        void onLogout();

        void searchTermEntered();

        void onProfileButton();
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


        if(this.showOverviewController == null){
            System.out.println("Show controller is not intialized");
        }
        else{
            System.out.println("Show controller is intialized");

        }


        profileButton.setOnAction(actionEvent -> {

            if (listener != null) {
                listener.onProfileButton();
            }
        });

    }

    //getters for MainController to use
    public BorderPane getHomePage() {
        return this.homePage;
    }

    public BorderPane getSearchPage() {
        return this.searchPage;
    }

    public BorderPane getShowOverview() {
        return this.showOverview;
    }

    public BorderPane getProfile() {
        return this.profile;
    }

    public HomePageController getHomePageController() {
        return this.homePageController;
    }

    public SearchPageController getSearchPageController() {
        return this.searchPageController;
    }

    public ShowOverviewController getShowOverviewController() {
        return this.showOverviewController;
    }

    public ProfileController getProfileController() {
        return this.profileController;
    }



    public void showOverviewControllerStatus(){
        if (this.showOverviewController == null ){
            System.out.println("Show is null bro");

        }
        else{
            System.out.println("we are NOT null");
        }
    }

    //this is we at search control would go
    private void searchTest() {

        searchPageController.novaLuna(searchBar.getText());
        listener.searchTermEntered();

    }

    
     

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }


}//end class */
