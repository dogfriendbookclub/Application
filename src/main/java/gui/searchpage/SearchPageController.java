package gui.searchpage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class SearchPageController implements Initializable {

    @FXML
    private ListView listResults;

    @FXML
    private TextField searchBar;

    private APIclient apIclient = new APIclient();

    @FXML
    private ListView<ShowPreview> testListView;

    @FXML
    private BorderPane rootPane;

    @FXML
    private ScrollPane rowOne;

    /**
     * Called to initialize a controller after its root element has been
//     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */

    private void handleShowClick(ShowPreview showPreview){
    }

    public interface SearchPageListener {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("SearchPageController is being initialized");



        try {
            // Fetch the list of popular shows (we assume the API fetch returns at least 45 for this example)
            List<ShowPreview> showPreviews = apIclient.fetchPopularShows();

            // Check if the list is null or empty
            if (showPreviews == null || showPreviews.isEmpty()) {
                System.out.println("No shows were fetched. The list is null or empty.");
                return; // Exit early if there's no data to process
            }

            // Divide the list into separate lists of 5 shows each
            List<ShowPreview> rowOneShows = showPreviews.subList(0, Math.min(5, showPreviews.size()));

            // Set each ListView with the corresponding list and custom cell factory
            setupListView(testListView, rowOneShows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures a ListView to display a list of ShowPreview objects with a custom cell factory.
     */
    private void setupListView(ListView<ShowPreview> listView, List<ShowPreview> shows) {
        listView.setItems(FXCollections.observableArrayList(shows));
        listView.setOrientation(Orientation.HORIZONTAL);

        // Configure the cell factory
        listView.setCellFactory(param -> new ListCell<ShowPreview>() {
            private final ImageView imageView = new ImageView();
            private final Label titleLabel = new Label();
            private final VBox cellContent = new VBox(imageView, titleLabel); // Use VBox directly

            {

                // Set padding and alignment for cell content
                cellContent.setAlignment(Pos.CENTER);
                cellContent.setSpacing(5);

                // Set the graphic to the cell content
                setGraphic(cellContent);
            }

            @Override
            protected void updateItem(ShowPreview showPreview, boolean empty) {
                super.updateItem(showPreview, empty);

                if (empty || showPreview == null) {
                    setGraphic(null);
                } else {
                    // Set the image and title for each cell
                    String posterUrl = "https://image.tmdb.org/t/p/w500" + showPreview.getPosterPath();
                    imageView.setImage(new Image(posterUrl));
                    titleLabel.setText(showPreview.getTitle());

                    // Bind the image width to the ListView's width to adapt when resizing
                    double padding = 10; // Padding around the image
                    double imageWidth = (listView.getWidth() / 5) - (padding * 2); // Calculate width per image minus padding
                    imageView.setFitHeight(122); // Fixed height
                    imageView.setFitWidth(imageWidth); // Adapt width

                    // Maintain the aspect ratio
                    imageView.setPreserveRatio(true);

                    // Update the graphic for the ListCell
                    setGraphic(cellContent);

                    // Add click event to the image
                    imageView.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) { // Respond to double-click
                            try {
                                loadShowOverview(showPreview);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

        // Set a fixed height for each cell
        listView.setFixedCellSize(150);
        listView.setPrefHeight(150);

        // Update the ListView's width to allow resizing
        listView.widthProperty().addListener((observable, oldValue, newValue) -> {
            listView.requestLayout(); // Request a layout update to adapt to the new width
        });
    }

    @FXML
    // Update to call loadShowOverview with the clicked show
    private void loadShowOverview(ShowPreview showPreview) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();

        /* If you need to pass data to the next controller, you can do it here
        ShowOverviewController controller = loader.getController();
        controller.setShowDetails(showPreview);  Assuming you have a method to set show details */

        // Replace the current root pane with the new one
        rootPane.getChildren().setAll(pane);
    }
}
