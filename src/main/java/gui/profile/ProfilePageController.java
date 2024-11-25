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

    private ProfilePageListener listener;

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

        Image image = new Image(getClass().getResource("fxml/userprofile/rottenPotatoAvatar.png").toExternalForm());
        profilePic.setImage(image);
        /*profileClip.setCenterX(profilePic.getFitWidth() / 2);
        profileClip.setCenterY(profilePic.getFitHeight() / 2);
        profilePic.setClip(profileClip); */

        username.setText(contentController.getUser().getUserHandle());

    }

    public void setProfilePageListener(ProfilePageListener listener) {
        this.listener = listener;
    }

    public interface ProfilePageListener {
        void showClickedOnInHome(int id);
    }
}
