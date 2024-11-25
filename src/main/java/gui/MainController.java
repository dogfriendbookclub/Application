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


    //change name
    private void buildingMaps(){
        //creating controllers
        homePageController = this.contentController.getHomePageController();
        searchPageController = this.contentController.getSearchPageController();
        showOverviewController = this.contentController.getShowOverviewController();
        seasonOverviewController = this.showOverviewController.getSeasonOverviewController();
        episodeOverviewController = this.showOverviewController.getEpisodeOverviewController();

        //has parent level of 0

        this.testMap.put("loginView",  new ViewNode(login, 0));
        this.testMap.put("contentView",  new ViewNode(content, 0));

        this.testMap.put("homeView",   new ViewNode(this.contentController.getHomePage(),1  ));
        this.testMap.put("searchView", new ViewNode(  this.contentController.getSearchPage(),1 ));
        this.testMap.put("showView", new ViewNode(this.contentController.getShowOverview(),1 ));
        this.testMap.put("showBox", new ViewNode(  this.contentController.getSearchPage(),1 ));

        this.testMap.put("seasonView", new ViewNode(this.showOverviewController.getSeasonPage(),2));
        this.testMap.put("episodeView", new ViewNode(  this.showOverviewController.getEpisodePage(),2 ));


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

        this.currentView = new StringBuilder("loginView");
    }

    //needs cleanup, repeating logic
    private void changeView (String newView) {
        //debug
        System.out.println("currentView before change: " + currentView.toString() );
        //checks for  validKey
        if (!this.testMap.containsKey(newView)) {
            throw new IllegalArgumentException(newView + "");
        }

        //checks to see if this is the same pane
        if (newView.compareTo(this.currentView.toString()) == 0) {
            System.out.println("this is the same pane");
        }
        else {
            //turn off currentView

            //has the same parent
            if( this.testMap.get(this.currentView.toString()).getLevel() == this.testMap.get(newView).getLevel() ) {
                this.testMap.get(this.currentView.toString()).getView().setVisible(false);
            }

            //going down
            if(this.testMap.get(newView).getLevel() > this.testMap.get(this.currentView.toString()).getLevel()) {
                //checking if we are not content view
                if ( this.currentView.toString().compareTo("contentView") != 0) {
                    //is contentview on? for example we might be going from login to somewher in content
                    if(!this.testMap.get("contentView").getView().isVisible()){
                        this.testMap.get("contentView").getView().setVisible(true);
                    }
                    //turn current view off
                    //this.testMap.get(this.currentView.toString()).getView().setVisible(false);
                }
                else{
                    if(this.testMap.get("homeView").getView().isVisible()){ //also check if new view is homeView
                        this.testMap.get("homeView").getView().setVisible(false);
                    }
                }

                //episode or season
                if(this.testMap.get(newView).getLevel() == 2){
                    //checkign if we are not show view and if show view is off
                    if ( this.currentView.toString().compareTo("showView") != 0 && !this.testMap.get("showView").getView().isVisible()) {
                        this.testMap.get("showView").getView().setVisible(true);
                    }
                    //checking if show box is off
                    if (!this.viewMap.get("showBox").isVisible() ){
                        this.viewMap.get("showBox").setVisible(true); //make sure showBox is on
                    }
                }
            }

            //special cases
                //going to showView
            if (newView.compareTo("showView") == 0){
                if (!this.viewMap.get("showBox").isVisible() ){
                        this.viewMap.get("showBox").setVisible(true); //make sure showBox is on
                    }
                }

                // somewhere in level 0 going to content
                if (newView.compareTo("contentView") == 0){
                    if (!this.viewMap.get("homeView").isVisible() ){
                        this.viewMap.get("homeView").setVisible(true); //check if home is on
                    }
                }







            //  parent directly to child
            else if ((this.parentMap.get(this.currentView.toString()) - this.parentMap.get(newView)) == -1) {
                // level 0 to level 1
                if ( this.parentMap.get(this.currentView.toString()) == 0 ) {
                    // checks if currentView is contentPane
                    if ((currentView.toString()).compareTo("contentView") != 0) {
                        this.viewMap.get(currentView.toString()).setVisible(false);
                        this.viewMap.get("contentView").setVisible(true);
                    }

                    //newView isnt homeView and homeView is on
                    if ((newView.compareTo("homeView") != 0) && this.viewMap.get("homeView").isVisible()) {
                        this.viewMap.get("homeView").setVisible(false);
                        if(newView.compareTo("showView") == 0){
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                    else { // newView is homeView or homeView is off //this shouldnt work vbut does
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                }

                //level 1 to level 2
                else if( this.parentMap.get(this.currentView.toString()) == 1){
                    if ((currentView.toString()).compareTo("showView") != 0) { //we are NOT show but wnat ot access thigns in show box
                        this.viewMap.get(currentView.toString()).setVisible(false);
                        this.viewMap.get("showView").setVisible(true);
                    }

                    //now showView is off and content is on
                    if ((newView.compareTo("showBox") != 0) && this.viewMap.get("showBox").isVisible()) { //newView isnt showBox(obv) and showBox is on
                        this.viewMap.get("showBox").setVisible(false);
                    }
                    else { // newView is showBox or showBox is off
                        if (!this.viewMap.get("showBox").isVisible()) {
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                }
            } //end level diff of 1

            //level 0 to level 2
            else if((this.parentMap.get(this.currentView.toString()) - this.parentMap.get(newView)) == -2){// login or content DIRECTLY to season or episode
                if ((currentView.toString()).compareTo("contentView") != 0) { //currentView is login  and going to into the contents of children
                    this.viewMap.get(currentView.toString()).setVisible(false);
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
                //         this.viewMap.get(newView).setVisible(true); //set whatever new pane is on

            } //end level diff 2

            //child to parent
            else{
                this.viewMap.get(this.currentView.toString()).setVisible(false);
                // this.viewMap.get(newView).setVisible(true);

                //episode/season to anything inside content container
                if ((this.parentMap.get(this.currentView.toString()) - this.parentMap.get(newView)) == 1){
                    //check for show first
                    if(newView.compareTo("showView") == 0) {
                        if (!this.viewMap.get("showBox").isVisible()) {
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                    else{
                        this.viewMap.get("showView").setVisible(false);
                    }

                    if (newView.compareTo("contentView") == 0){
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                    if (newView.compareTo("loginView") == 0){
                        this.viewMap.get("contentView").setVisible(false);
                    }
                }

                if ((this.parentMap.get(this.currentView.toString()) - this.parentMap.get(newView)) == 2){
                    this.viewMap.get("showView").setVisible(false);
                    this.viewMap.get("contentView").setVisible(false);
                }

            }

            this.viewMap.get(newView).setVisible(true); //set whatever new pane is on

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
    public void likedShow() {

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
