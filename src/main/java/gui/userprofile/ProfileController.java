package gui.userprofile;

import edu.metrostate.User;
import gui.content.ContentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ImageView profilePic;

    @FXML
    private Circle profileClip;

    @FXML
    private Text username;

    @FXML
    private ProfileListener profileListener;

    public interface ProfileListener{

        User getUser();
    }


    ProfileListener listener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image(getClass().getResource("/fxml/userprofile/rottenPotatoAvatar.png").toExternalForm());
        profilePic.setImage(image);

        // username.setText(listener.getUser().getUserHandle());

    }

    public void setProfileListener(ProfileListener listener){
        this.listener = profileListener;
    }
}
