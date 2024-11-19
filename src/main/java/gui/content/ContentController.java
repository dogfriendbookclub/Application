package gui.content;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {
    private APIclient apIclient = new APIclient();

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView image1, image2, image3, image4, image5;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;

    @FXML
    private StackPane stackPane; // Assuming your main layout is a StackPane

    @FXML
    private BorderPane homePage; // FXML for home page
    @FXML
    private BorderPane searchPage; // FXML for search page
    @FXML
    private BorderPane showPage; // FXML for show overview page

    private ContentListener listener;

    @FXML
    void loadShowOverview(ActionEvent event) {
        switchView(showPage);
    }

    @FXML
    void loadHomePage(ActionEvent event) {
        switchView(homePage);
    }

    @FXML
    void loadSearchPage(ActionEvent event) {
        // Fetch search results as before
        try {
            List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
            for (ShowPreview result : testResults) {
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Search API Test Error");
            e.printStackTrace();
        }




        switchView(searchPage);
    }

    private void switchView(BorderPane view) {
        homePage.setVisible(false);
        searchPage.setVisible(false);
        showPage.setVisible(false);

        if (view != null) {
            view.setVisible(true);
        }
    }

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ContentController is being initialized");

        logoutButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onLogout();
            }
        });

        // Initially set all pages to be invisible except the home page
        homePage.setVisible(true);
        searchPage.setVisible(false);
        showPage.setVisible(false);
    }

    public interface ContentListener {
        void onLogout();
    }
}
