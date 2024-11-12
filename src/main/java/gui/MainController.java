package gui;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import gui.content.ContentController;
import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener {
//    THE WHOLE POINT OF THE INTERFACES IS FOR THINGS WHEN WE WANT TO SWICH BETWEEN SCREENS!!!!!!!!
//    FOR EXAMPLE THE SEARCH BAR, HOMEBUTTON, THE DROP DOWN MENUS, LOGIN LOGOUT, INTERACTING WITH SHOWS !!!!

    @FXML
    private Parent login;

    @FXML
    private Parent content;

    @FXML
    private LoginController loginController;

    @FXML
    private ContentController contentController;

    private Pane currentNode;

    private StringBuilder currentPane;


    private HashMap<String, Pane> viewMap = new HashMap<>();

    private MutableValueGraph<Pane, Integer> graph =
                ValueGraphBuilder.directed().allowsSelfLoops(true).build();

    //asynchrnous queue system, datamodel.

    private void loadPaneViews (String name, String fxmlpath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
        Pane view;
        try {
            view = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        //this try and catch thing is kinda..........lame idk if it was worth putting all this time into
        try {
            loadPaneViews("viewOne", "/fxml/content/Content.java");
            loadPaneViews("episode", "/fxml/episodeoverview/EpisodeOverview.java");
            loadPaneViews("home", "/fxml/homepage/HomePage.java");
            loadPaneViews("search", "/fxml/searchPage/searchpage.java");
            loadPaneViews("season", "/fxml/seasonoverview/SeasonOverview.java");
            loadPaneViews("show", "/fxml/showoverview/ShowOverview.java");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        //LOADING FXML FILES, FUNCTION WAS BUGGY
        FXMLLoader episodeloader = new FXMLLoader(getClass().getResource("/fxml/episodeoverview/EpisodeOverview.fxml"));
        Pane episode;
        try {
            episode = episodeloader.load();
             this.viewMap.put("episodeView", episode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader homeloader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));
        Pane home;
        try {
            home = homeloader.load();
            this.viewMap.put("homeView", home);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FXMLLoader searchloader = new FXMLLoader(getClass().getResource("/fxml/searchPage/searchpage.fxml"));
        Pane search;
        try {
            search = searchloader.load();
            this.viewMap.put("searchView", search);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FXMLLoader seasonloader = new FXMLLoader(getClass().getResource("/fxml/seasonoverview/SeasonOverview.fxml"));
        Pane season;
        try {
            season = seasonloader.load();
            this.viewMap.put("seasonView", season);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader showloader = new FXMLLoader(getClass().getResource( "/fxml/showoverview/ShowOverview.fxml"));
        Pane show;
        try {
            show = showloader.load();
            this.viewMap.put("showView", show);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FXMLLoader seasonloader = new FXMLLoader(getClass().getResource("/fxml/seasonoverview/SeasonOverview.fxml"));
        Pane season;
        try {
            season = seasonloader.load();
            this.viewMap.put("seasonView", season);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //LOADING MAP WITH FXML FILES
        graph.putEdgeValue(home, show, 1);
        graph.putEdgeValue(show, home, 1);

        graph.putEdgeValue(home, search, 1);
        graph.putEdgeValue(search, home, 1);

        graph.putEdgeValue(search, show, 1);
        graph.putEdgeValue(show, search, 1);

        graph.putEdgeValue(show, season, 1);
        graph.putEdgeValue(season, episode, 1);
        graph.putEdgeValue(episode, season, 1);



        this.currentNode = viewMap.get("homeView");
        this.loginController.setLoginListener(this);
        this.content.setVisible(false);
        this.login.setVisible(true);
        this.contentController.setContentListener(this);
        this.contentController.getHomePage().setVisible(true);
        this.currentPane = new StringBuilder("homeView");

    }


    private void changeView (String newPane){
        if(newPane.compareTo(this.currentPane.toString()) == 0){
            System.out.println("this is the same pane");
        }

        this.viewMap.get(this.currentPane.toString()).setVisible(false);
        this.viewMap.get(newPane).setVisible(true);
        this.currentPane.setLength(0);
        this.currentPane.append(newPane);

        /*
        else {
            newPane.compareTo(this.currentPane.toString());
            if (newPane.compareTo("searchView") == 0) {
                this.get()


            }
    */

           // this.contentController.setVisible();
        }

    @Override
    public void onLoginComplete() {
        this.login.setVisible(false);
        this.content.setVisible(true);
        this.contentController.getHomePage().setVisible(true);
        this.contentController.getSearchPage().setVisible(false);
        this.contentController.getShowPage().setVisible(false);
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

        this.login.setVisible(true);
        this.content.setVisible(false);

    }
    /**
     *
     */
    @Override
    public void loadShowOverview() {
        System.out.println("load show works :)");
        this.contentController.getHomePage().setVisible(false);
        this.contentController.getSearchPage().setVisible(false);
        this.contentController.getShowPage().setVisible(true);
        changeView("homeView");

    }

    /**
     *
     */
    @Override
    public void loadSearchPage() {
        System.out.println("load search works :)");
        this.contentController.getHomePage().setVisible(false);
      this.contentController.getSearchPage().setVisible(true);
        this.contentController.getShowPage().setVisible(false);

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

