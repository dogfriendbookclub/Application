package edu.metrostate.gui.image;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ImageController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Pane imageContainer;

    @FXML
    private Button uploadButton;

    @FXML
    private VBox vBox;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setPreserveRatio(true);
        try {
            URL placeholder = getClass().getResource("/edu/metrostate/gui/placeholder-image.png");
            Image placeholderImage = new Image(placeholder.toURI().toString(), 300, 300, true, true);
            imageView.setImage(placeholderImage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        uploadButton.setOnAction(actionEvent -> {
            handleUpload();
        });
    }

    private void handleUpload() {
        Stage stage = (Stage) this.uploadButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File file = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            imageView.setFitWidth(imageContainer.getWidth());
        } catch (Exception exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error loading image: " + file.getName());
            alert.show();

            long timestamp = 100L;
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime dateTime = LocalDateTime.from(instant);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
            String displayString = formatter.format(dateTime);
        }
    }
}
