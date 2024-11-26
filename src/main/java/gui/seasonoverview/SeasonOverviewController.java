package gui.seasonoverview;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Objects;

import edu.metrostate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.metrostate.APIclient;
import edu.metrostate.Season;
import edu.metrostate.Show;
import edu.metrostate.ShowPreview;
import edu.metrostate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SeasonOverviewController implements Initializable {
    private APIclient apIclient = new APIclient();

    @FXML
    private ListView creatorList;
    @FXML ListView castList;
    @FXML
    private Label seasonNum;
    @FXML
    private Text reviewRate;

    @FXML
    private Text featureUsername;

    @FXML
    private Text featureDate;


    @FXML
    public TextArea featureReview;

    @FXML
    public Text seasonRate;

    @FXML
    public TextField userReview;




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

    }
    public void loadSeasonData(Season season) throws IOException {
        List<String> mainCast = apIclient.fetchCastForSeason(season);
        ObservableList<String> observableMainCastList = FXCollections.observableArrayList(mainCast);
        castList.setItems(observableMainCastList);
        seasonNum.setText(Integer.toString(season.getSeasonNumber()));

    }
    public void populatecreators(Show show){
        List<Creator> creators = show.getCreators();  // Get the creators from the Show class
        // Create a list to store the creator names
        List<String> creatorNames = new ArrayList<>();
        for (Creator creator : creators) {
            creatorNames.add(creator.getName());  // Add creator name to the list
        }

        // Set the creator names to the ListView
        ObservableList<String> observableCreatorNames = FXCollections.observableArrayList(creatorNames);
        creatorList.setItems(observableCreatorNames);


    }

}

