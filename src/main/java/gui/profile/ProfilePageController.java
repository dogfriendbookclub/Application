package gui.profile;

import gui.content.ContentController;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {


    @FXML
    private ImageView profilePic;

    @FXML
    private Circle profileClip;

    @FXML
    private Text username;

    @FXML
    private ContentController contentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //NOT THE FILE WE'RE USING

    }


}
