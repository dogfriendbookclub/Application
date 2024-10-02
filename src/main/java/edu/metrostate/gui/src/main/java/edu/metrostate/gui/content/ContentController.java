package edu.metrostate.gui.content;

import edu.metrostate.gui.studentlist.StudentListController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentController implements Initializable {

    public interface ContentListener {
        void onLogout();
    }

    @FXML
    private Button logoutButton;

    @FXML
    private TabPane tabPane;

    private ContentListener listener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onLogout();
            }
        });
    }

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }
}
