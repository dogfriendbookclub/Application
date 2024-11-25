package gui.content;

import edu.metrostate.APIclient;
import edu.metrostate.User;
import gui.homepage.HomePageController;
import gui.login.LoginController;
import gui.userprofile.ProfileController;
import gui.searchpage.SearchPageController;
import gui.showoverview.ShowOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
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

    @FXML
    private Button profileButton;

    //end interface stuff

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

    @FXML
    private LoginController loginController;


    private ContentListener listener;

    private User user;

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

*/

    public interface ContentListener {
        void onHomeButton();

        void onLogout();

        void searchTermEntered();

        void loadShowOverviewPage();

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

    public HomePageController getHomePageController() {
        return this.homePageController;
    }

    public SearchPageController getSearchPageController() {
        return this.searchPageController;
    }

    public ShowOverviewController getShowOverviewController() {
        return this.showOverviewController;
    }


    public BorderPane getProfile() {
        return this.profile;
    }

    public ProfileController getProfileController() {
        return this.profileController;
    }


    //this is we at search control would go
    private void searchTest() {

        searchPageController.novaLuna(searchBar.getText());
        listener.searchTermEntered();

    }

    public User getUser() {
        System.out.println("getting user " + user.getUserHandle() + "to send to profile controller");
        return loginController.getUser();
    }


/*
    //THERE were a couple of search functions,  I combined them together
    @FXML
    void searchTermEntered(ActionEvent event) throws IOException {
     //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));
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

      //  BorderPane pane = loader.load();
   //     rootPane.getChildren().setAll(pane);
        showPage.setVisible(false);
        homePage.setVisible(false);
        searchPage.setVisible(true);
*/

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }


}//end class */
