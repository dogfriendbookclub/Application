package gui.userprofile;

import edu.metrostate.User;
import gui.content.ContentController;
import gui.login.LoginController;
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

    private LoginController.LoginListener loginListener;

    private User user = new User();


    public interface ProfileListener{

        User passUser();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image("https://cdn.drawception.com/images/avatars/698725-186.png");
        profilePic.setImage(image);

        // username.setText(listener.passUser().getUserHandle());
        // System.out.println("set username on profile page to " + listener.passUser().getUserHandle());


    }

    public interface ProfilePageListener {
        void showClickedOnInHome(int id);
    }

    public void setUser(User u) {
        this.user.setUserHandle(u.getUserHandle());
        this.user.setId(u.getUserId());
    }

    public void setProfileListener(ProfileListener listener){
        this.listener = listener;
    }
}
