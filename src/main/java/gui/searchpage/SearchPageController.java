package gui.searchpage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;




public class SearchPageController implements Initializable {

    @FXML
    private ListView<ShowPreview> listResults;

    private APIclient apIclient = new APIclient();

    private SearchPageListener listener;


    public interface SearchPageListener{
       // onFilter selected and what not
       void showSelected(int id);
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


    private void setupListView(ListView<ShowPreview> listView, List<ShowPreview> shows) {
        listView.setItems(FXCollections.observableArrayList(shows));
        listView.setOrientation(Orientation.VERTICAL);

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
                            listener.showSelected( showPreview.getShowId());
                            System.out.println("Show has been selected in searchPAge Controller");
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


    //does all the search stuff
    public void novaLuna(String term){
        try {
            // Fetch the list of popular shows (we assume the API fetch returns at least 45 for this example)
            List<ShowPreview> showPreviews = apIclient.fetchSearchResults(term);

            // Check if the list is null or empty
            if (showPreviews == null || showPreviews.isEmpty()) {
                System.out.println("No shows were fetched. The list is null or empty.");
                return; // Exit early if there's no data to process
            }

            // Divide the list into separate lists of 5 shows each
            List<ShowPreview> rowOneShows = showPreviews.subList(0, Math.min(10, showPreviews.size()));

            // Set each ListView with the corresponding list and custom cell factory
            setupListView(listResults, rowOneShows);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void setSearchPageListener(SearchPageListener listener){
        this.listener = listener;
    }

}//end class
