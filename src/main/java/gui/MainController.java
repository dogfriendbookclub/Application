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

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener, ShowOverviewController.ShowOverviewListener{
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


    //@FXML

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
        Parent contentParent = this.viewMap.get("contentView").getParent();
        Parent homeParent = this.viewMap.get("homeView").getParent();
        Parent showParent = this.viewMap.get("showView").getParent();
        Parent seasonParent = this.viewMap.get("seasonView").getParent();

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
        this.currentPane = new StringBuilder("loginView");
    }


    private void changeView (String newPane) {
        //if statement would check if string is a valid key
        //one thing i need to also figure out is waht the current parent key is
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
        if (!this.viewMap.containsKey(newPane)) {
            throw new IllegalArgumentException(newPane + "");
        }
        //checks to see if this is the same pane
        if (newPane.compareTo(this.currentPane.toString()) == 0) {
            System.out.println("this is the same pane");
        }

        else {
            //has the same parent
            if( this.parentMap.get(newPane) == this.parentMap.get(this.currentPane.toString()) ){
                this.viewMap.get(this.currentPane.toString()).setVisible(false);
                this.viewMap.get(newPane).setVisible(true);

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
                if ( this.parentMap.get(this.currentPane.toString()) == 0 ) { // content and login to child of content
                    if ((currentPane.toString()).compareTo("contentView") == 1) { //currentPane is login  and going to into the contents of children
                        this.viewMap.get(currentPane.toString()).setVisible(false);
                        this.viewMap.get("contentView").setVisible(true);
                    }

                    //now login is off and content is on
                    if ((newPane.compareTo("homeView") == 1) && this.viewMap.get("homeView").isVisible()) { //newPane isnt homeView and homeView is on
                        this.viewMap.get("homeView").setVisible(false);
                    }
                    else { // newPane is homeView or homeView is off
                        if (!this.viewMap.get("homeView").isVisible()) {
                            this.viewMap.get("homeView").setVisible(true);
                        }
                    }

                    this.viewMap.get(newPane).setVisible(true); //set whatever new pane is on
                }

                //something in currentPane going to somethign in showContainer
                if( this.parentMap.get(this.currentPane.toString()) == 1){
                        if ((currentPane.toString()).compareTo("showView") == 1) { //we are NOT show but wnat ot access thigns in show box
                            this.viewMap.get(currentPane.toString()).setVisible(false);
                            this.viewMap.get("showView").setVisible(true);
                        }

                        //now showView is off and content is on
                        if ((newPane.compareTo("showBox") == 1) && this.viewMap.get("showBox").isVisible()) { //newPane isnt showBox(obv) and showBox is on
                            this.viewMap.get("showBox").setVisible(false);
                        }
                        else { // newPane is showBox or showBox is off
                            if (!this.viewMap.get("showBox").isVisible()) {
                                this.viewMap.get("showBox").setVisible(true);
                            }
                        }
                }
            } //end level diff of 1

            //parent level directly to its children
            else if((this.parentMap.get(this.currentPane.toString()) - this.parentMap.get(newPane)) == -2){// login or content DIRECTLY to season or episode
                if ((currentPane.toString()).compareTo("contentView") == 1) { //currentPane is login  and going to into the contents of children
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

            }


            else{
                this.viewMap.get(this.currentPane.toString()).setVisible(false);
                this.viewMap.get(newPane).setVisible(true);

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
            this.currentPane.setLength(0);
            this.currentPane.append(newPane);


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



/*
            if (newPane.compareTo("loginView") == 0) {
                this.viewMap.get("contentView").setVisible(false);
                this.viewMap.get("contentView").getParent();
            }
            else if (newPane.compareTo("contentView") == 0) {
                this.viewMap.get("contentView").setVisible(true);
                this.viewMap.get("homeView").setVisible(true);
            }
*/


        }
    }
    @Override
    public void onLoginComplete() {
        changeView("contentView");

//        this.login.setVisible(false);

        //      this.contentController.getHomePage().setVisible(true);
        //      this.contentController.getSearchPage().setVisible(false);
        //   this.contentController.getShowPage().setVisible(false);
 //       this.content.setVisible(true);


    }



    /**
     *
     */
    @Override
    public void loadHomePage() {
        System.out.println("load homepage works :)");
   //     changeView("homeView");
   //     this.contentController.getHomePage().setVisible(true);
        changeView("homeView");

//        this.contentController.getSearchPage().setVisible(false);
 //       this.contentController.getShowPage().setVisible(false);
    }

    /**
     *
     */
    @Override
    public void onLogout() {
        System.out.println("load loagoutr works :)");
        changeView("loginView");
     //   this.login.setVisible(true);
    //    this.content.setVisible(false);

    }
    /**
     *
     */
    @Override
    public void loadShowOverview() {
        System.out.println("load show works :)");
    //    this.contentController.getHomePage().setVisible(false);
  //      this.contentController.getSearchPage().setVisible(false);
//        this.contentController.getShowOverview().setVisible(true);
        changeView("showView");

    }

    /**
     *
     */
    @Override
    public void loadSearchPage() {
        System.out.println("load search works :)");
        changeView("searchView");
//        this.contentController.getHomePage().setVisible(false);
//      this.contentController.getSearchPage().setVisible(true);
//        this.contentController.getShowOverview().setVisible(false);

    }



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

