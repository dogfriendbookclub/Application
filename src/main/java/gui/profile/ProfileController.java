package gui.profile;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    public interface ProfileListener{

    }


    ProfileListener listener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setProfileListener(ProfileListener listener){
        this.listener = listener;
    }
}
