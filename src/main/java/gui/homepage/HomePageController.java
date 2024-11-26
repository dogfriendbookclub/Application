package gui.homepage;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class HomePageController implements Initializable {
    private HomePageListener listener;

    private APIclient apIclient = new APIclient();

    @FXML
    private ListView<ShowPreview> testListView;

    @FXML
    private ListView<ShowPreview> testListView1;

    @FXML
    private ListView<ShowPreview> testListView11;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HomepageController is being initialized");

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
            List<ShowPreview> rowTwoShows = showPreviews.size() > 5 ? showPreviews.subList(5, Math.min(10, showPreviews.size())) : List.of();
            List<ShowPreview> rowThreeShows = showPreviews.size() > 10 ? showPreviews.subList(10, Math.min(15, showPreviews.size())) : List.of();

            // Set each ListView with the corresponding list and custom cell factory
            setupListView(testListView, rowOneShows);
            setupListView(testListView1, rowTwoShows);
            setupListView(testListView11, rowThreeShows);

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


                //pass ID Into show
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
                                listener.showClickedOnInHome(showPreview.getShowId());
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



    public interface HomePageListener {
        void showClickedOnInHome(int id);
    }


    public void setHomePageListener(HomePageListener listener){
        this.listener = listener;
    }

/*
    @FXML
    // Update to call loadShowOverviewPage with the clicked show
    private void showClickedOnInHome(ShowPreview showPreview) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();

         If you need to pass data to the next controller, you can do it here
        ShowOverviewController controller = loader.getController();
        controller.setShowDetails(showPreview);  Assuming you have a method to set show details

    C:\Users\lemon\OneDrive\Documents\git_repositories\ics372_project_1_rep\Application\src\main\resources\fxml\showoverview\ShowOverview.fxml

        // Replace the current root pane with the new one
        rootPane.getChildren().setAll(pane);
    }
*/





}//end class