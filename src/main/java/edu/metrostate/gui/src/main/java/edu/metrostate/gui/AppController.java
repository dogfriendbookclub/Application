package edu.metrostate.gui;

import edu.metrostate.gui.content.ContentController;
import edu.metrostate.gui.login.LoginController;
import edu.metrostate.gui.studentlist.StudentListController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable, LoginController.LoginListener, ContentController.ContentListener {
    @FXML
    private Parent loginForm;

    @FXML
    private Parent mainContent;

    @FXML
    private LoginController loginFormController;

    @FXML
    private ContentController mainContentController;

    @Override
    public void onLoginComplete() {
        this.loginForm.setVisible(false);
        this.mainContent.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginFormController.setLoginListener(this);
        this.mainContent.setVisible(false);
        this.loginForm.setVisible(true);
        this.mainContentController.setContentListener(this);
    }

    @Override
    public void onLogout() {
        this.loginForm.setVisible(true);
        this.mainContent.setVisible(false);
    }
}
