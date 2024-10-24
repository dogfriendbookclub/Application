package gui.homepage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.net.URL;
import java.util.ResourceBundle;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class HomePageController implements Initializable {
    private APIclient apIclient = new APIclient();

    @FXML
    private VBox vboxContainer;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button showOverviewButton;

    @FXML
    public void initialize() {
        try {
            // Fetch the list of popular shows (limited to 15)
            List<ShowPreview> showPreviews = apIclient.fetchPopularShows();

            // Check if the list is null or empty
            if (showPreviews == null || showPreviews.isEmpty()) {
                System.out.println("No shows were fetched. The list is null or empty.");
                return; // Exit early if there's no data to process
            }

            // Maximum number of images per row
            int maxImagesPerRow = 5;

            // Temporary HBox to hold images and titles
            HBox currentHBox = new HBox(10); // 10 is the spacing between images
            currentHBox.setAlignment(javafx.geometry.Pos.CENTER);

            // Iterate through the list of ShowPreview
            for (int i = 0; i < showPreviews.size(); i++) {
                ShowPreview showPreview = showPreviews.get(i);

                // Fetch the poster URL using the showId
                String posterUrl = apIclient.fetchPosterImageUrl(showPreview.getShowId());

                // Check if the poster URL is valid
                if (posterUrl == null || posterUrl.isEmpty()) {
                    System.out.println("Poster URL is null or empty for show ID: " + showPreview.getShowId());
                    continue; // Skip this show if there's no valid image
                }

                // Create an ImageView for each poster
                ImageView imageView = new ImageView();
                imageView.setFitWidth(96); // Set the desired width for the images
                imageView.setFitHeight(122); // Set the desired height for the images
                imageView.setPreserveRatio(true);

                // Load the image
                try {
                    Image image = new Image(posterUrl);
                    imageView.setImage(image);
                } catch (Exception e) {
                    System.out.println("Failed to load image for show ID: " + showPreview.getShowId());
                    e.printStackTrace();
                    continue; // Skip this image if there's a loading error
                }

                // Create a Label for the show title
                Label titleLabel = new Label(showPreview.getTitle());
                titleLabel.setWrapText(true); // Enable wrapping for longer titles

                // Create a VBox to hold the ImageView and the Label
                VBox showVBox = new VBox(imageView, titleLabel);
                showVBox.setAlignment(javafx.geometry.Pos.CENTER); // Center align items in VBox


                // Make the VBox clickable
                showVBox.setOnMouseClicked(event -> {
                    // Action to be taken when clicked
                    System.out.println("Clicked on: " + showPreview.getShowId());
                    try {
                        loadShowOverview(null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // You can access other properties of showPreview here, such as showPreview.getShowId()
                });

                // Change cursor on hover
                showVBox.setOnMouseEntered(event -> showVBox.setCursor(Cursor.HAND));
                showVBox.setOnMouseExited(event -> showVBox.setCursor(Cursor.DEFAULT));

                // Add the VBox to the current HBox
                currentHBox.getChildren().add(showVBox);

                // If the row is filled or it's the last image, add the current HBox to the VBox
                if ((i + 1) % maxImagesPerRow == 0 || i == showPreviews.size() - 1) {
                    vboxContainer.getChildren().add(currentHBox);
                    currentHBox = new HBox(10); // Create a new HBox for the next row
                    currentHBox.setAlignment(javafx.geometry.Pos.CENTER);
                }

            }

        } catch (IOException e) {
            System.out.println("An IOException occurred during API call.");
            e.printStackTrace();
        }


    }





    @FXML
    void loadShowOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadHomePage(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage/HomePage.fxml"));

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void loadSearchPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        // returns a list of results (to the console atm) for the search query with the text from the search bar
        try {
            List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
            for ( ShowPreview result : testResults) {
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Search API Test Error");
            e.printStackTrace();
        }

        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
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

    }
}