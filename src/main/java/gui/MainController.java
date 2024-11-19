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
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener, HomePageController.HomePageListener, ShowOverviewController.ShowOverviewListener, SearchPageController.SearchPageListener {
//    THE WHOLE POINT OF THE INTERFACES IS FOR THINGS WHEN WE WANT TO SWICH BETWEEN SCREENS!!!!!!!!
//    FOR EXAMPLE THE SEARCH BAR, HOMEBUTTON, THE DROP DOWN MENUS, LOGIN LOGOUT, INTERACTING WITH SHOWS !!!!

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

    private HashMap<String, Node> viewMap = new HashMap<>();
    private HashMap<String, Integer> parentMap = new HashMap<>();


    //asynchrnous queue system, datamodel.


    private void buildingMap(){
        homePageController = this.contentController.getHomePageController();
        searchPageController = this.contentController.getSearchPageController();
        showOverviewController = this.contentController.getShowOverviewController();
        seasonOverviewController = this.showOverviewController.getSeasonOverviewController();
        episodeOverviewController = this.showOverviewController.getEpisodeOverviewController();

        this.viewMap.put("loginView", login);
        this.viewMap.put("contentView", content);

        this.viewMap.put("homeView", this.contentController.getHomePage());
        this.viewMap.put("searchView", this.contentController.getSearchPage());
        this.viewMap.put("showView", this.contentController.getShowOverview());

        this.viewMap.put("showBox", this.showOverviewController.getShowBox());
        this.viewMap.put( "seasonView", this.showOverviewController.getSeasonPage());
        this.viewMap.put( "episodeView", this.showOverviewController.getEpisodePage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildingMap();
        this.viewMap.get("loginView").setVisible(true);
        this.viewMap.get("contentView").setVisible(false);
        this.viewMap.get("searchView").setVisible(false);
        this.viewMap.get("showView").setVisible(false);
        this.viewMap.get("episodeView").setVisible(false);
        this.viewMap.get("seasonView").setVisible(false);

        this.parentMap.put("contentView", 0);
        this.parentMap.put("loginView", 0);
        this.parentMap.put("homeView", 1);
        this.parentMap.put("searchView", 1);
        this.parentMap.put("showView", 1);
        this.parentMap.put("showBox", 1);
        this.parentMap.put("seasonView", 2);
        this.parentMap.put("episodeView", 2);

        this.loginController.setLoginListener(this);
        this.contentController.setContentListener(this);
        this.showOverviewController.setShowOverviewListener(this);
        this.homePageController.setHomePageListener(this);
        this.searchPageController.setSearchPageListener(this);

        this.currentPane = new StringBuilder("loginView");
    }

    private void changeView (String newPane) {
        //if statement would check if string is a valid key
        //one thing i need to also figure out is waht the current parent key is

        System.out.println("currentPane before cahnge: " +currentPane.toString() );
        /*
        login
        content
            home
            search
            show
                showBox
                season
                episode
         */

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
//                this.viewMap.get(newPane).setVisible(true);

                //somewhere in contnt container going toshow
                if (newPane.compareTo("showView") == 0){
                    if (!this.viewMap.get("showBox").isVisible() ){
                        this.viewMap.get("showBox").setVisible(true);
                    }
                }


                if (newPane.compareTo("contentView") == 0){
                    if (!this.viewMap.get("homeView").isVisible() ){
                        this.viewMap.get("homeView").setVisible(true);
                    }
                }

            }

            // parent level directly to its children
            else if ((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == -1) {
                // content and login to child of content
                if ( this.parentMap.get(this.currentPane.toString()) == 0 ) {
                    //currentPane is login  and going to into the contents of children
                    if ((currentPane.toString()).compareTo("contentView") != 0) {
                        this.viewMap.get(currentPane.toString()).setVisible(false);
                        this.viewMap.get("contentView").setVisible(true);
                    }

                    //now login is off and content is on
                    if ((newPane.compareTo("homeView") != 0) && this.viewMap.get("homeView").isVisible()) { //newPane isnt homeView and homeView is on
                        this.viewMap.get("homeView").setVisible(false);
                        if(newPane.compareTo("showView") == 0){
                            this.viewMap.get("showBox").setVisible(true);
                        }
                    }
                    else { // newPane is homeView or homeView is off
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                }

                //something in currentPane going to somethign in showContainer
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

            //parent level directly to grand children
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

            else{
                //children to parent
                this.viewMap.get(this.currentPane.toString()).setVisible(false);
               // this.viewMap.get(newPane).setVisible(true);

                //episode to show or anythin else or content container to show
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
            /*
                big issues
                login -> search, home, show, showContainer    0 to 1
                    issue: content wouldnt turn on (for show container Show wouldnt turn on alongSide content not turning on)

                content -> anything in in its container (i.e home, search)  0 to 1
                    isssue: content would turn itself off making evrything in its contianer inivisbe

                show -> showcontainer 1 to 2
                    isssue: show would turn itself off making evrything in its contianer inivisbe

                contentContainer -> showContainer 1 to 2
                    issue: contentContainer would turn itself off but not show on thus making show invisible
                    SPECIAL CASE: when it goes to show i must be able to manage showBox

                showContainer -> anything in contentContainer(including show) 2 to 1
                    issues: show congtainer would turn itself off but not show
                        when it goes to show show would turn on BUT not

                shoBox should only be used with show and should never be called on itself
             */
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
    public void showSelected() {
        changeView("showView");
    }

    //end show itnerface







/*
    THE WHOLE POINT OF THE INTERFACES IS FOR THINGS WHEN WE WANT TO SWICH BETWEEN SCREENS!!!!!!!!
    FOR EXAMPLE THE SEARCH BAR, HOMEBUTTON, THE DROP DOWN MENUS, LOGIN LOGOUT, INTERACTING WITH SHOWS !!!!

    @Override
    public void onHomeButton() {

    }


    @Override
    public void onSearchbar() {


    }
*/



}

