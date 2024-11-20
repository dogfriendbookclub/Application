package gui.content;

import edu.metrostate.APIclient;
import edu.metrostate.ShowPreview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {
    private APIclient apIclient = new APIclient();

    public Button logoutButton;
    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private TextField searchBar;

    @FXML
    private Button homeButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private BorderPane homePage;

    @FXML
    private BorderPane searchPage;

    @FXML
    private BorderPane showPage;

    @FXML
    private Button showOverviewButton;

    private ContentListener listener;



    @FXML
    void loadShowOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showoverview/ShowOverview.fxml"));
        BorderPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void loadHomePage(ActionEvent actionEvent) {
        homePage.setVisible(false);
        searchPage.setVisible(false);
        showPage.setVisible(true);

    }


    public interface ContentListener {
        void onLogout();
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
        System.out.println("contentcontroller is am being initialized");

        logoutButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onLogout();
            }
        });

        searchPage.setVisible(false);
        showPage.setVisible(false);

    }

    //this function is what i use to show and hide the different screens, this is only temporary


    //THERE were a couple of search functions,  I combined them together
    @FXML
    void loadSearchPage(ActionEvent evnt) throws IOException {
     //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchpage/SearchPage.fxml"));

        // returns a list of results (to the console atm) for the search query with the text from the search bar
        try {
            List<ShowPreview> testResults = apIclient.fetchSearchResults(searchBar.getText());
            for ( ShowPreview result : testResults) {
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            System.out.println("Search API Test Error");
            e.printStackTrace();
        }

)


      //  BorderPane pane = loader.load();
   //     rootPane.getChildren().setAll(pane);
        showPage.setVisible(false);
        homePage.setVisible(false);
        searchPage.setVisible(true);


    }

    public void setContentListener(ContentListener listener) {
        this.listener = listener;
    }


}//end class
