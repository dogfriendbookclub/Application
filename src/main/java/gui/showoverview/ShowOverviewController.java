package gui.showoverview;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import edu.metrostate.APIclient;
import edu.metrostate.Show;
import edu.metrostate.ShowPreview;
import gui.content.ContentController;
import gui.episodeoverview.EpisodeOverviewController;
import gui.seasonoverview.SeasonOverviewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowOverviewController implements Initializable {
    @FXML
    private ListView<String> cretaorsList;

    @FXML
    private ListView<String> mainCastList;


    @FXML
    private TextArea synopsisTextBox;

    @FXML
    private HBox featuredStars;

    @FXML
    private Label featuredReviewTextBox;

    @FXML
    private MenuButton seasonButton;

    @FXML
    private MenuItem episodeSelector;

    @FXML
    private MenuButton episodeButton;

     @FXML
    //void loadHomePage(ActionEvent event) throws IOException {
    private HBox yourStars;

    @FXML
    private TextField userShowReview;

    /* @FXML
    private ComboBox<String> seasonButton;

    @FXML
    private ComboBox<String> episodeButton; */

    @FXML
    private HBox showBox;

    @FXML
    private BorderPane seasonPage;

    @FXML
    private BorderPane episodePage;


    @FXML
    private SeasonOverviewController seasonOverviewController;

    @FXML
    private EpisodeOverviewController episodeOverviewController;

    private APIclient apIclient = new APIclient();

    private ShowOverviewListener listener;


    //getters for MainController
    public HBox getShowBox(){
         return this.showBox;
    }

    public BorderPane getSeasonPage(){
         return this.seasonPage;
    }

    /*
    void loadSearchPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    } */

    public BorderPane getEpisodePage(){
         return this.episodePage;
    }

    public EpisodeOverviewController getEpisodeOverviewController(){
         return this.episodeOverviewController;
    }

    public SeasonOverviewController getSeasonOverviewController(){
        return this.seasonOverviewController;
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
        //do whatever

       /* seasonButton.setOnAction(actionEvent -> {
            seasonPreviews();
        });

        episodeButton.setOnAction(actionEvent -> {
            episodePreviews();
       }); */
            for (Season season : show.getSeasons()) {
                MenuItem seasonItem = new MenuItem("Season " + season.getSeasonNumber());

                seasonItem.setOnAction(event -> {
                    seasonButton.setText(seasonItem.getText());
                    System.out.println("Selected " + seasonItem.getText());

                    episodeButton.getItems().clear();
                    episodeButton.setText("Select Episode");

                    for (Episode episode : season.getEpisodes()) {
                        MenuItem episodeItem = new MenuItem("Episode " + episode.getEpisodeNum());
                        System.out.println("Episode " + episode.getEpisodeNum() + " added to selector");

                        episodeItem.setOnAction(event2 -> {
                            episodeButton.setText(episodeItem.getText());
                            System.out.println("Selected " + episodeItem.getText());
                        });

                        episodeButton.getItems().add(episodeItem);
                    }
                });

                seasonButton.getItems().add(seasonItem);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch data", e);
        }
    }



    public void loadShowData(int id) throws IOException {
        Show show = apIclient.fetchShowData(id);
        synopsisTextBox.clear();
        synopsisTextBox.appendText(show.getPremise());
    }



    public void seasonPreviews() {
        //do whatever


    }

    public void episodePreviews() {
        //do whatever
    }

/*
            for (Season season : show.getSeasons()) {
                MenuItem seasonItem = new MenuItem("Season " + season.getSeasonNumber());

                seasonItem.setOnAction(event -> {
                    seasonButton.setText(seasonItem.getText());
                    System.out.println("Selected " + seasonItem.getText());

                    episodeButton.getItems().clear();
                    episodeButton.setText("Select Episode");

                    for (Episode episode : season.getEpisodes()) {
                        MenuItem episodeItem = new MenuItem("Episode " + episode.getEpisodeNum());
                        System.out.println("Episode " + episode.getEpisodeNum() + " added to selector");

                        episodeItem.setOnAction(event2 -> {
                            episodeButton.setText(episodeItem.getText());
                            System.out.println("Selected " + episodeItem.getText());
                        });

                        episodeButton.getItems().add(episodeItem);
                    }
                });

                seasonButton.getItems().add(seasonItem);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch data", e);
        }
    }

*/


    public interface ShowOverviewListener{
        void selectedSeason();
        void selectedEpisode();


    }


    public void setShowOverviewListener(ShowOverviewListener listener) {
        this.listener = listener;
    }


}
