package gui.userprofile;

import edu.metrostate.User;
import gui.content.ContentController;
import gui.profile.ProfilePageController;
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

    private ProfileListener listener;


    public interface ProfileListener{

        User getUser();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image(getClass().getResource("/fxml/userprofile/rottenPotatoAvatar.png").toExternalForm());
        profilePic.setImage(image);

        // username.setText(listener.getUser().getUserHandle());
        // System.out.println("set username on profile page to " + listener.getUser().getUserHandle());


    }

    public interface ProfilePageListener {
        void showClickedOnInHome(int id);
    }

    public void setProfileListener(ProfileListener listener){
        this.listener = listener;
    }
}
