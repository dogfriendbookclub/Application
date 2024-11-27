package gui.login;

import edu.metrostate.User;
import gui.userprofile.ProfileController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ProfileController.ProfileListener {
    @FXML
    private TextField errorBox;


    @FXML
    private VBox vBoxOne;

    @FXML
    private VBox vBoxTwo;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button signInButton;

    private LoginListener loginListener;

    private String acceptedUser;

    private String acceptedPass;

    private boolean firstTime = false;

    private User user;

    private ProfileController profileController;

    @Override
    public User passUser() {
        return user;
    }
    //private ProfilePageListener listener;


    public interface LoginListener {
        void onLoginComplete(); //change name too similair to teachers

        void getUser();
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
        this.errorBox.setVisible(false);
        this.vBoxTwo.setVisible(true);
        signInButton.setOnAction(actionEvent -> {
            signInCheck();
        });
    }

    private void signInCheck(){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(firstTime){
            System.out.println(acceptedUser);
            System.out.println(acceptedPass);
            if (username != null && (username.compareTo(acceptedUser) == 0) && password != null &&
                    (password.compareTo(acceptedPass) == 0) ) {
                System.out.println(username);
                System.out.println(password);
                this.errorBox.setVisible(false);
                performLogin();
            }
            else{
                showLoginError();
            }
        }
        else {
            if (username == null || username.isBlank() || password == null || password.isBlank()) {
                showLoginError();
            } else {
                firstTime = true;
                acceptedUser = username;
                acceptedPass = password;
                performLogin();
            }
        }

        user = new User(acceptedUser);
        profileController.setUser(user);
        System.out.println("user " + user.getUserHandle() + " created with id " + user.getUserId());
    }


    private void performLogin() {
        if (loginListener != null) {
            //   this.vBoxTwo.setVisible(false);
            //  this.vBoxOne.setVisible(false);
            loginListener.onLoginComplete();
        }
    }

    private void showLoginError() {//changes this so it jsut appears over textBOX
        this.errorBox.setVisible(true);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    /*public void setProfilePageListener(ProfilePageListener listener) {
        this.listener = listener;
    }*/

    public interface ProfilePageListener {
        void showClickedOnInHome(int id);
    }

    /*
    public User getUser() {
        return this.user;
    } */

}



