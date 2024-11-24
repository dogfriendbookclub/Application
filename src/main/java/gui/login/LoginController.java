package gui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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

    public interface LoginListener {
        void onLoginComplete(); //change name too similair to teachers
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
    }


    private void performLogin() {
        if (loginListener != null) {
            //   this.vBoxTwo.setVisible(false);
            //  this.vBoxOne.setVisible(false);
            loginListener.onLoginComplete();
        }
    }

    private void backGroundImage(){}


    private void showLoginError() {//changes this so it jsut appears over textBOX
        this.errorBox.setVisible(true);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }




}



