package gui.showoverview;

import edu.metrostate.Creator;
import edu.metrostate.migrations.Migrations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowOverviewController implements Initializable {

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
    private VBox backdropBackground;

    @FXML
    private Label showTitle;

    @FXML
    private Label yearsAired;


    @FXML
    private SeasonOverviewController seasonOverviewController;

    @FXML
    private EpisodeOverviewController episodeOverviewController;

    private APIclient apIclient = new APIclient();

    private ShowOverviewListener listener;

    private Connection connection = null;

    private Migrations migrations = new Migrations();




    //getters for MainController
    public HBox getShowBox(){
        return this.showBox;
    }

    public BorderPane getSeasonPage(){
        return this.seasonPage;
    }



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

        seasonButton.setOnAction(actionEvent -> {
            seasonPreviews();
        });

        episodeButton.setOnAction(actionEvent -> {
            episodePreviews();
        });

        likeButton.setOnAction(actionEvent -> {
            listener.likedShow();
        });


    }

    public void loadShowData(int id) throws IOException {
        Show show = apIclient.fetchShowData(id);
        for (Season season : show.getSeasons()) {
            season.setShowId(id);
            season.addAllEpisodes();
        }
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
            MenuItem seasonItem = new MenuItem(season.getName());

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

        connectTest();


        userShowReview.setOnAction(actionEvent -> {
            try {
                writeShowReview(userShowReview.getText(), 5, show.getShowId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                dbUtil.closeQuietly(connection);
            }

        });
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

    public void writeShowReview(String reviewText, int rate, int showId) throws SQLException {

        Review review = new Review(reviewText, rate, showId, MediaType.SHOW);
        review.insert(connection);

    }

    public static void populate(Connection connection) {
        List<Review> reviews = new ArrayList<>();
        Review test1 = new Review("test text", 8, 4321, 123);
        Review test2 = new Review("test testing testing", 3, 4568, 565);
        Review test3 = new Review("mcTest", 5, 987352, 987);

        reviews.add(test1);
        reviews.add(test2);
        reviews.add(test3);

        for (Review review : reviews) {
            review.insert(connection);
            System.out.println("printing review...");
            System.out.println("text: " + review.getReviewText());
            System.out.println("stars: " + review.getStars());
            System.out.println("showId: " + review.getShowId());
            System.out.println("reviewId: " + review.getReviewId());
        }
    }

    public static List<Review> load(Connection connection) {
        List<Review> reviews = Review.loadAll(connection);
        return reviews;
    }

    public void seasonPreviews() {
        //do whatever
    }

    public void episodePreviews() {
        //do whatever
    }


    public void connectTest() {
        try {
            Boolean populate = true;
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(Database.connectionString);

            migrations.runMigrations(connection);

            if (populate) {
                populate(connection);
            }

            List<Review> reviews = load(connection);
            reviews.forEach(person -> System.out.println(reviews));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtil.closeQuietly(connection);
        }
    }

    public interface ShowOverviewListener{
        void selectedSeason();
        void selectedEpisode();
        void likedShow();
    }


    public void setShowOverviewListener(ShowOverviewListener listener) {
        this.listener = listener;
    }


}