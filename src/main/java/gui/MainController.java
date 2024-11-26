package gui;

import edu.metrostate.Review;
import edu.metrostate.Season;
import gui.content.ContentController;
import gui.episodeoverview.EpisodeOverviewController;
import gui.homepage.HomePageController;
import gui.login.LoginController;
import gui.searchpage.SearchPageController;
import gui.seasonoverview.SeasonOverviewController;
import gui.showoverview.ShowOverviewController;
import gui.userprofile.ProfileController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener, HomePageController.HomePageListener, ShowOverviewController.ShowOverviewListener, SearchPageController.SearchPageListener, ProfileController.ProfileListener {



    @FXML
    private Pane login;

    @FXML
    private BorderPane content;

    @FXML
    private LoginController loginController;

    @FXML
    private ContentController contentController;

    @FXML
    private ShowOverviewController showOverviewController;

    @FXML
    private HomePageController homePageController;

    @FXML
    private SearchPageController searchPageController;


    @FXML
    private SeasonOverviewController seasonOverviewController;


    @FXML
    private EpisodeOverviewController episodeOverviewController;

    @FXML
    private ProfileController profileController;

    @FXML
    private StringBuilder currentView;

    //contains references to each fxml file
    private HashMap<String, Node> viewMap = new HashMap<>();

    //this is used in to switch between fxml files that are of different hierarchy
    private HashMap<String, Integer> parentMap = new HashMap<>();

    private HashMap<String, ViewNode> testMap= new HashMap<>();
        /*


        the value stored is the parent "level"
        the lower the ID, the higher hierarchy:

                             main
                            /     \
                           /       \
                          /         \
                         /           \
        level 0        login     content
                                 /   |   \
                                /    |    \
                               /     |     \
                              /      |      \
        level 1             home   search   show
                                          /   |   \
                                         /    |    \
                                        /     |     \
                                       /      |      \
        level 2                     showBox  season episode


         */

    //asynchrnous queue system, datamodel.

    private void buildingMaps(){
        //creating controllers
        homePageController = this.contentController.getHomePageController();
        searchPageController = this.contentController.getSearchPageController();
        showOverviewController = this.contentController.getShowOverviewController();
        profileController = this.contentController.getProfileController();
        seasonOverviewController = this.showOverviewController.getSeasonOverviewController();
        episodeOverviewController = this.showOverviewController.getEpisodeOverviewController();


        /*
        wrok on user profile
       // work on reviews, be able to do it for SHOWS.
            making it look nice
            and trying to make less coupled
        */
        //has parent level of 0
        this.testMap.put("loginView",  new ViewNode(login, 0));
        this.testMap.put("contentView",  new ViewNode(content, 0));
        this.testMap.put("homeView",   new ViewNode(this.contentController.getHomePage(),1  ));
        this.testMap.put("searchView", new ViewNode(  this.contentController.getSearchPage(),1 ));
        this.testMap.put("profileView", new ViewNode(this.contentController.getProfile(), 1));

        this.testMap.put("showView", new ViewNode(this.contentController.getShowOverview(),1 ));
        this.testMap.put("showBox", new ViewNode(  this.showOverviewController.getShowBox(),1 ));
        this.testMap.put("seasonView", new ViewNode(this.showOverviewController.getSeasonOverview(),2));
        this.testMap.put("episodeView", new ViewNode(  this.showOverviewController.getEpisodeOverview(),2 ));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buildingMaps();
        this.testMap.get("loginView").getView().setVisible(true);
        this.testMap.get("contentView").getView().setVisible(false);
        this.testMap.get("searchView").getView().setVisible(false);
        this.testMap.get("showView").getView().setVisible(false);
        this.testMap.get("episodeView").getView().setVisible(false);
        this.testMap.get("seasonView").getView().setVisible(false);
        this.testMap.get("profileView").getView().setVisible(false);

        this.loginController.setLoginListener(this);
        this.contentController.setContentListener(this);
        this.showOverviewController.setShowOverviewListener(this);
        this.homePageController.setHomePageListener(this);
        this.searchPageController.setSearchPageListener(this);
        this.profileController.setProfileListener(this);

        this.currentView = new StringBuilder("loginView");
    }



    //change name



    //needs cleanup, repeating logic
    private void changeView (String newView) {
        //debug
        System.out.println("currentView before change: " + currentView.toString() );
        //checks for  validKey
        if (!this.testMap.containsKey(newView)) {
            throw new IllegalArgumentException(newView + "");
        }

        //checks to see if this is the same pane
        /*
        if (newView.compareTo(this.currentView.toString()) == 0) {
            System.out.println("this is the same pane");
        }*/
        else {
            //turn off currentView
            //has the same parent
            if( this.testMap.get(this.currentView.toString()).getLevel() == this.testMap.get(newView).getLevel() ) {
                this.testMap.get(this.currentView.toString()).getView().setVisible(false);
            }

            //going down
            else if(this.testMap.get(newView).getLevel() > this.testMap.get(this.currentView.toString()).getLevel()) {
                //checking if we are not content view
                if (this.currentView.toString().compareTo("contentView") != 0) {
                    //we are not cntentvirw
                    //is contentview on? for example we might be going from login to somewher in content
                    if (!this.testMap.get("contentView").getView().isVisible()) { // if content vi3ew is not visible
                        this.testMap.get(this.currentView.toString()).getView().setVisible(false); //we are not content view or one of its children
                        this.testMap.get("contentView").getView().setVisible(true);
                    }
                }
                else{ //we are contentView which must mean home is on
                    this.testMap.get("homeView").getView().setVisible(false);
                }
                //episode or season
                if (this.testMap.get(newView).getLevel() == 2) {
                    //checkign if we are not show view and if show view is off
                    if (this.currentView.toString().compareTo("showView") != 0) { // are we are not showView
                        if(this.testMap.get(this.currentView.toString()).getLevel() == 1){// are we on its level?
                            this.testMap.get(this.currentView.toString()).getView().setVisible(false); // yes, turn us off
                        }
                        this.testMap.get("showView").getView().setVisible(true);
                    }
                    else{
                        this.testMap.get("showBox").getView().setVisible(false); //we must be showView which must mena showBox is on
                    }
                }
            }

            //going up
            else {
                this.testMap.get(this.currentView.toString()).getView().setVisible(false);
                if(this.testMap.get(this.currentView.toString()).getLevel() == 2 && newView.compareTo("showView") != 0){
                    this.testMap.get("showView").getView().setVisible(false);
                }
                if(newView.compareTo("loginView") == 0){

                    this.testMap.get("contentView").getView().setVisible(false);
                }
            }

            this.testMap.get(newView).getView().setVisible(true); //set whatever new pane is on
            //special cases
            //going to showView
            if (newView.compareTo("showView") == 0){
                if (!this.testMap.get("showBox").getView().isVisible() ){
                        this.testMap.get("showBox").getView().setVisible(true); //make sure showBox is on
                    }
                }

                // somewhere in level 0 going to content
            if (newView.compareTo("contentView") == 0){
                    if (!this.testMap.get("homeView").getView().isVisible() ){
                        this.testMap.get("homeView").getView().setVisible(true); //check if home is on
                    }
                }

            this.currentView.setLength(0);
            this.currentView.append(newView);
            System.out.println("currentView after cahnge: " + currentView.toString() );
        }
    }

    //begin from login interface
    @Override
    public void onLoginComplete() {
        changeView("contentView");
    }
    //end from login interface


    //Begin from Content interface
    /**
     *
     */
    @Override
    public void onHomeButton() {
        System.out.println("load homepage works :)");
        changeView("homeView");
    }

    /**
     *
     */
    @Override
    public void onLogout() {
        System.out.println("load loagoutr works :)");
        changeView("loginView");

    }

    /**
     *
     */
    @Override
    public void searchTermEntered() {
        changeView("searchView");
    }

    /**
     *
     */
    @Override
    public void onProfileButton() {


    }
    //end from content interface



    //from HomePageController, homepage interface
    /**
     *
     */
    @Override
    public void showClickedOnInHome(int id) {
        try {
            this.showOverviewController.loadShowData(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeView("showView");
    }

    //end from homepage interface


    //begin show interface
    /**
     *
     */
    @Override
    public void selectedSeason(Season season) {
        try {
            this.seasonOverviewController.loadSeasonData(season);
        } catch (IOException e) {
           e.printStackTrace();
        }
        changeView("seasonView");
    }

    /**
     *
     */
    @Override
    public void selectedEpisode() {
        System.out.println("episdo eselected");
        changeView("episodeView");

    }

    /**
     *
     */
    @Override
    public void likedShow() {

    }

    /**
     * @param review
     */
    @Override
    public void submittedReview(Review review) {
        System.out.println("we really got a review");
        //assign to a profile

    }


    /**
     *
     */
    @Override
    public void showSelected(int id) {

        try {
            this.showOverviewController.loadShowData(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeView("showView");
    }

    //end show itnerface







/*

    @Override
    public void onHomeButton() {

    }


    @Override
    public void onSearchbar() {


    }
*/



}
