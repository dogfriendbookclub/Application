package gui.showoverview;

import edu.metrostate.Creator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.metrostate.*;
import edu.metrostate.*;
import edu.metrostate.APIclient;
import edu.metrostate.Season;
import edu.metrostate.Show;
import edu.metrostate.ShowPreview;
import gui.content.ContentController;
import gui.episodeoverview.EpisodeOverviewController;
import gui.seasonoverview.SeasonOverviewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static edu.metrostate.MediaType.SHOW;

public class ShowOverviewController implements Initializable {

    @FXML
    private Text voteCount;
    @FXML
    private TextField userRate;
    @FXML
    private Button reviewButton;

    @FXML
    private Button likeButton;

    @FXML
    private StackPane imageStack;

    @FXML
    private  ImageView showImages;

    @FXML
    private ListView<String> creatorsList;

    @FXML
    private ListView<String> mainCastList;

    @FXML
    private TextArea synopsisTextBox;

    @FXML
    private Label featuredReviewTextBox;

    @FXML
    private MenuButton seasonButton;

    @FXML
    private MenuButton episodeButton;

    @FXML
    private TextField userShowReview;


    @FXML
    private HBox showBox;

    @FXML
    private BorderPane seasonOverview;

    @FXML
    private BorderPane episodeOverview;

    @FXML
    private VBox backdropBackground;

    @FXML
    private Label showTitle;

    @FXML
    private Label yearsAired;
    @FXML
    private Text averageRate;

    @FXML
    private SeasonOverviewController seasonOverviewController;

    @FXML
    private EpisodeOverviewController episodeOverviewController;

    private APIclient apIclient = new APIclient();

    private ShowOverviewListener listener;

    private String reviewText;

    private double rating;



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
        UnaryOperator<TextFormatter.Change> rateFilter = Change ->{
            String input = Change.getControlNewText();

            if(input.length() < 2 && (input.matches("[0-9]") || input.matches("\\d*"))){
                return Change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(rateFilter);

        userRate.setTextFormatter(textFormatter);
        seasonButton.setOnAction(actionEvent -> {
            seasonPreviews();
        });

        episodeButton.setOnAction(actionEvent -> {
            episodePreviews();
        });

        likeButton.setOnAction(actionEvent -> {
            listener.likedShow();
        });
        userShowReview.setOnKeyPressed(keyEvent ->{
                    if(keyEvent.getCode().toString().equals("ENTER")){
                        reviewCheck();
                    }
                }

        );

        userRate.setOnAction(actionEvent -> {
            rateCheck();
        });


        reviewButton.setOnAction(actionEvent -> {
            testReview();
        });

    }

    public void loadShowData(int id) throws IOException {
        Show show = apIclient.fetchShowData(id);
        voteCount.setText("(" + show.getVoteCount() + ")");
        seasonButton.getItems().clear();
        for (Season season : show.getSeasons()) {
            season.setShowId(id);
            season.addAllEpisodes();
        }
        averageRate.setText(String.format("%.1f", show.getStars()));
        seasonButton.getItems().clear();
        yearsAired.setText(show.getYearStart());
        showTitle.setText(show.getTitle());
        synopsisTextBox.clear();
        synopsisTextBox.appendText(show.getPremise());
        creatorsList.getItems().clear();
        populatecreators(show);
        mainCastList.getItems().clear();
        populateCast(id);
        imageTest(show.getPosterPath());

        for (Season season : show.getSeasons()) {
            if (season.getSeasonNumber() > 0) {
                MenuItem seasonItem = new MenuItem(season.getName());

                seasonItem.setOnAction(event -> {
                    seasonButton.setText(seasonItem.getText());
                    System.out.println("Selected " + seasonItem.getText());
                    if (listener != null) { // Ensure the listener is set
                        listener.selectedSeason(show, season); // Call the listener's selectedSeason function
                    }
                    episodeButton.getItems().clear();
                    episodeButton.setText("Select Episode");



                    for (Episode episode : season.getEpisodes()) {
                        MenuItem episodeItem = new MenuItem("Episode " + episode.getEpisodeNum());
                        System.out.println("Episode " + episode.getEpisodeNum() + " added to selector");

                        episodeItem.setOnAction(event2 -> {
                            episodeButton.setText(episodeItem.getText());
                            System.out.println("Selected " + episodeItem.getText());
                            if(listener != null){
                                listener.selectedEpisode(show, season, episode);
                            }
                        });

                        episodeButton.getItems().add(episodeItem);
                    }
                });

                seasonButton.getItems().add(seasonItem);
            }
        }


    }


    //reviews, use reviewws fxml, create list view
    private void reviewCheck(){
        this.reviewText = userShowReview.getText();
    }


    private void rateCheck(){
        this.rating = Math.ceil(Double.parseDouble(userRate.getText()));
    }


    private void testReview(){
        Review userReview = new Review( reviewText,rating, SHOW);
        this.listener.submittedReview(userReview);

    }


    // same function as nicks setVBoxBackdrop, jsut with an image
    private void imageTest(String backDropPath){
        if (backDropPath != null && !backDropPath.isEmpty()) {
            try {
                // Construct the full URL for the image
                String fullImageUrl = "https://image.tmdb.org/t/p/original" + backDropPath;

                Image image = new Image(fullImageUrl);

                showImages.setImage(image);

            } catch (Exception e) {
                System.err.println("Failed to set backdrop VBox background: " + e.getMessage());
            }
        } else {
            System.out.println("Backdrop path is invalid or empty.");
        }
    }

    public void populatecreators(Show show){
        List<Creator> creators = show.getCreators();  // Get the creators from the Show class
        // Create a list to store the creator names
        List<String> creatorNames = new ArrayList<>();
        for (Creator creator : creators) {
            creatorNames.add(creator.getName());  // Add creator name to the list
        }

        // Set the creator names to the ListView
        ObservableList<String> observableCreatorNames =
                FXCollections.observableArrayList(creatorNames);
        creatorsList.setItems(observableCreatorNames);
    }
    public void populateCast(int id) throws IOException {
        List<String> mainCast = apIclient.fetchMainCast(id);
        ObservableList<String> observableMainCastList = FXCollections.observableArrayList(mainCast);
        mainCastList.setItems(observableMainCastList);
    }



    public void seasonPreviews() {
        //do whatever
    }

    public void episodePreviews() {
        //do whatever
    }


    public interface ShowOverviewListener{
        void selectedSeason(Show show, Season season);
        void selectedEpisode(Show show, Season season, Episode episode);
        void likedShow();
        void submittedReview(Review review);
    }


    public void setShowOverviewListener(ShowOverviewListener listener) {
        this.listener = listener;
    }
    //getters for MainController
    public HBox getShowBox(){
        return this.showBox;
    }

    public BorderPane getSeasonPage(){
        return this.seasonOverview;
    }



    public BorderPane getEpisodePage(){
        return this.episodeOverview;
    }

    public EpisodeOverviewController getEpisodeOverviewController(){
        return this.episodeOverviewController;
    }

    public SeasonOverviewController getSeasonOverviewController(){
        return this.seasonOverviewController;
    }


}