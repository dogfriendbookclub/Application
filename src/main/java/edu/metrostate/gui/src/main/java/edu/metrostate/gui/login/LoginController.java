package edu.metrostate.gui.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public interface LoginListener {
        void onLoginComplete();
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private LoginListener loginListener;

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(actionEvent -> {
            checkLogin();
        });
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            showLoginFormError();
        } else {
            showConfirmLogin();
        }
    }

    private void showConfirmLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Alert alert = new Alert(Alert.AlertType.NONE, "Login", ButtonType.YES, ButtonType.NO);
        alert.setContentText("Would you like to log in with\nUsername: " + username + "\nPassword: " + password);
        alert.showAndWait()
                .filter(button -> button == ButtonType.YES)
                .ifPresent(response -> performLogin());
    }

    private void showLoginFormError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        alert.setContentText("Username and Password are required.");
        alert.showAndWait();
    }

    private void performLogin() {
        if (loginListener != null) {
            loginListener.onLoginComplete();
        }
    }
}
