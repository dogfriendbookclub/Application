package gui;

import gui.content.ContentController;
import gui.episodeoverview.EpisodeOverviewController;
import gui.homepage.HomePageController;
import gui.login.LoginController;
import gui.searchpage.SearchPageController;
import gui.seasonoverview.SeasonOverviewController;
import gui.showoverview.ShowOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener, HomePageController.HomePageListener, ShowOverviewController.ShowOverviewListener, SearchPageController.SearchPageListener {

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
    private StringBuilder currentPane;

    //contains references to each fxml file
    private HashMap<String, Node> viewMap = new HashMap<>();

    //this is used in to switch between fxml files that are of different hierarchy
    private HashMap<String, Integer> parentMap = new HashMap<>();
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


    //change name
    private void buildingMaps(){
        //creating controllers
        homePageController = this.contentController.getHomePageController();
        searchPageController = this.contentController.getSearchPageController();
        showOverviewController = this.contentController.getShowOverviewController();
        seasonOverviewController = this.showOverviewController.getSeasonOverviewController();
        episodeOverviewController = this.showOverviewController.getEpisodeOverviewController();

        //has parent level of 0
        this.viewMap.put("loginView", login);
        this.viewMap.put("contentView", content);
        this.parentMap.put("contentView", 0);
        this.parentMap.put("loginView", 0);

        //has parent level of 1
        this.viewMap.put("homeView", this.contentController.getHomePage());
        this.viewMap.put("searchView", this.contentController.getSearchPage());
        this.viewMap.put("showView", this.contentController.getShowOverview());
        this.parentMap.put("homeView", 1);
        this.parentMap.put("searchView", 1);
        this.parentMap.put("showView", 1);
        this.parentMap.put("showBox", 1);

        //has parent level 3
        this.viewMap.put("showBox", this.showOverviewController.getShowBox());
        this.viewMap.put( "seasonView", this.showOverviewController.getSeasonPage());
        this.viewMap.put( "episodeView", this.showOverviewController.getEpisodePage());
        this.parentMap.put("seasonView", 2);
        this.parentMap.put("episodeView", 2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildingMaps();
        this.viewMap.get("loginView").setVisible(true);
        this.viewMap.get("contentView").setVisible(false);
        this.viewMap.get("searchView").setVisible(false);
        this.viewMap.get("showView").setVisible(false);
        this.viewMap.get("episodeView").setVisible(false);
        this.viewMap.get("seasonView").setVisible(false);


        this.loginController.setLoginListener(this);
        this.contentController.setContentListener(this);
        this.showOverviewController.setShowOverviewListener(this);
        this.homePageController.setHomePageListener(this);
        this.searchPageController.setSearchPageListener(this);

        this.currentPane = new StringBuilder("loginView");
    }

    //needs cleanup, repeating logic
    private void changeView (String newPane) {
        //debug
        System.out.println("currentPane before change: " +currentPane.toString() );

        //checks for  validKey
        if (!this.viewMap.containsKey(newPane)) {
            throw new IllegalArgumentException(newPane + "");
        }

        //checks to see if this is the same pane
        if (newPane.compareTo(this.currentPane.toString()) == 0) {
            System.out.println("this is the same pane");
        }

        else {
            //has the same parent
            if((this.parentMap.get(newPane)).equals(this.parentMap.get(this.currentPane.toString()))){
                this.viewMap.get(this.currentPane.toString()).setVisible(false);

                //somewhere in parent level 1 going to show
                if (newPane.compareTo("showView") == 0){
                    if (!this.viewMap.get("showBox").isVisible() ){
                        this.viewMap.get("showBox").setVisible(true); //make sure showBox is on
                    }
                }

                // somewhere in level 0 going to content
                if (newPane.compareTo("contentView") == 0){
                    if (!this.viewMap.get("homeView").isVisible() ){
                        this.viewMap.get("homeView").setVisible(true); //check if home is on
                    }
                }

            }

            //  parent directly to child
            else if ((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == -1) {
                // level 0 to level 1
                if ( this.parentMap.get(this.currentPane.toString()) == 0 ) {
                    // checks if currentPane is contentPane
                    if ((currentPane.toString()).compareTo("contentView") != 0) {
                        this.viewMap.get(currentPane.toString()).setVisible(false);
                        this.viewMap.get("contentView").setVisible(true);
                    }

                    //newPane isnt homeView and homeView is on
                    if ((newPane.compareTo("homeView") != 0) && this.viewMap.get("homeView").isVisible()) {
                        this.viewMap.get("homeView").setVisible(false);
                        if(newPane.compareTo("showView") == 0){
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                    else { // newPane is homeView or homeView is off //this shouldnt work vbut does
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                }

                //level 1 to level 2
                else if( this.parentMap.get(this.currentPane.toString()) == 1){
                        if ((currentPane.toString()).compareTo("showView") != 0) { //we are NOT show but wnat ot access thigns in show box
                            this.viewMap.get(currentPane.toString()).setVisible(false);
                            this.viewMap.get("showView").setVisible(true);
                        }

                        //now showView is off and content is on
                        if ((newPane.compareTo("showBox") != 0) && this.viewMap.get("showBox").isVisible()) { //newPane isnt showBox(obv) and showBox is on
                            this.viewMap.get("showBox").setVisible(false);
                        }
                        else { // newPane is showBox or showBox is off
                            if (!this.viewMap.get("showBox").isVisible()) {
                                this.viewMap.get("showBox").setVisible(true);
                            }
                        }
                }
            } //end level diff of 1

            //level 0 to level 2
            else if((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == -2){// login or content DIRECTLY to season or episode
                if ((currentPane.toString()).compareTo("contentView") != 0) { //currentPane is login  and going to into the contents of children
                    this.viewMap.get(currentPane.toString()).setVisible(false);
                    this.viewMap.get("contentView").setVisible(true);
                }

                if(this.viewMap.get("homeView").isVisible()){
                    this.viewMap.get("homeView").setVisible(false);
                }

                if(!this.viewMap.get("showView").isVisible()){
                    this.viewMap.get("showView").setVisible(true);
                }
                if(this.viewMap.get("showBox").isVisible()){
                    this.viewMap.get("showbox").setVisible(false);
                }
       //         this.viewMap.get(newPane).setVisible(true); //set whatever new pane is on

            } //end level diff 2

            //child to parent
            else{
                this.viewMap.get(this.currentPane.toString()).setVisible(false);
               // this.viewMap.get(newPane).setVisible(true);

                //episode/season to anything inside content container
                if ((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == 1){
                    //check for show first
                    if(newPane.compareTo("showView") == 0) {
                        if (!this.viewMap.get("showBox").isVisible()) {
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                    else{
                        this.viewMap.get("showView").setVisible(false);
                    }

                    if (newPane.compareTo("contentView") == 0){
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                    if (newPane.compareTo("loginView") == 0){
                        this.viewMap.get("contentView").setVisible(false);
                    }
                }

                if ((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == 2){
                    this.viewMap.get("showView").setVisible(false);
                    this.viewMap.get("contentView").setVisible(false);
                }

            }

            this.viewMap.get(newPane).setVisible(true); //set whatever new pane is on

            this.currentPane.setLength(0);
            this.currentPane.append(newPane);

            System.out.println("currentPane after cahnge: " +currentPane.toString() );
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
    public void loadShowOverviewPage() {
        System.out.println("load show works :)");
        changeView("showView");
    }

    /**
     *
     */
    @Override
    public void searchTermEntered() {
        changeView("searchView");
    }
    //end from content interface



    //from HomePageController, homepage interface
    /**
     *
     */
    @Override
    public void showClickedOnInHome() {
        changeView("showView");
    }

    //end from homepage interface

    //begin show interface
    /**
     *
     */
    @Override
    public void selectedSeason() {
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
    public void showSelected(int id) {

        try {
            this.showOverviewController.loadShowData(id);
        } catch (IOException e) {
             e.printStackTrace();
        }
        changeView("showView");
    }

    //end show itnerface







}

