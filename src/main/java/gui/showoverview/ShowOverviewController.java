package gui.showoverview;

import edu.metrostate.Creator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.metrostate.*;
import edu.metrostate.APIclient;
import edu.metrostate.Season;
import edu.metrostate.Show;
import gui.episodeoverview.EpisodeOverviewController;
import gui.seasonoverview.SeasonOverviewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static edu.metrostate.MediaType.SHOW;

public class ShowOverviewController implements Initializable {
    @FXML
    private TextField userRate;

    @FXML
    private Button reviewButton;

    @FXML
    private Text averageRate;

    @FXML
    private Text highestSeason;

    @FXML
    private Text highestEpisode;

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
    private TextArea featuredReviewTextBox;

    @FXML
    private MenuButton seasonButton;

    @FXML
    private MenuButton episodeButton;

    @FXML
    private TextArea userShowReview;

    @FXML
    private HBox showBox;

    @FXML
    private VBox backdropBackground;

    @FXML
    private Label showTitle;

    @FXML
    private Label yearsAired;

    @FXML
    private BorderPane seasonOverview;

    @FXML
    private BorderPane episodeOverview;

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


    /*
    for review
    user types review.
    review is saved to show
    review is also sent off to database
     */


    public void loadShowData(int id) throws IOException {
        Show show = apIclient.fetchShowData(id);
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
            MenuItem seasonItem = new MenuItem("Season " + season.getSeasonNumber());

            seasonItem.setOnAction(event -> {
                seasonButton.setText(seasonItem.getText());
                System.out.println("Selected " + seasonItem.getText());
                if (listener != null) { // Ensure the listener is set
                    this.listener.selectedSeason(season); // Call the listener's selectedSeason function
                }

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

    }


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


    //reviews, use reviewws fxml, create list view

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

    private void setVBoxBackdrop(String backDropPath) {
        if (backDropPath != null && !backDropPath.isEmpty()) {
            try {
                // Construct the full URL for the image
                String fullImageUrl = "https://image.tmdb.org/t/p/original" + backDropPath;

                // Set the background image
                backdropBackground.setStyle(
                        "-fx-background-image: url('" + fullImageUrl + "'); " +
                                "-fx-background-size: cover; " +
                                "-fx-background-position: center; " +
                                "-fx-background-repeat: no-repeat;"
                );
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
        void selectedSeason(Season season);
        void selectedEpisode();
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

    public BorderPane getSeasonOverview(){
        return this.seasonOverview;
    }

    public BorderPane getEpisodeOverview(){
        return this.episodeOverview;
    }

    public EpisodeOverviewController getEpisodeOverviewController(){
        return this.episodeOverviewController;
    }

    public SeasonOverviewController getSeasonOverviewController(){
        return this.seasonOverviewController;
    }




}
