package gui;

import gui.content.ContentController;
import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, LoginController.LoginListener, ContentController.ContentListener {
    @FXML
    private Parent login;

    @FXML
    private Parent content;

    @FXML
    private LoginController loginController;

    @FXML
    private ContentController contentController;


    @Override
    public void onLoginComplete() {
        this.login.setVisible(false);
        this.content.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginController.setLoginListener(this);
        this.content.setVisible(false);
        this.login.setVisible(true);
        this.contentController.setContentListener(this);
    }

    /**
     *
     */
    @Override
    public void onLogout() {
        this.content.setVisible(false);
        this.login.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void onHomeButton() {
    }
/*
    @Override
    public void onLogout() {
        this.login.setVisible(true);
        this.mainContent.setVisible(false);
    }

 */
}
