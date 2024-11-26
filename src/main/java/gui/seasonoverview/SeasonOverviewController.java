package gui.seasonoverview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import edu.metrostate.APIclient;
import edu.metrostate.Season;
import edu.metrostate.Show;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SeasonOverviewController implements Initializable {

    @FXML
    private ListView creatorsList;

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



    private APIclient apIclient = new APIclient();
    private Season selectedSeason;
    private Show selectedShow;


    @FXML
    private ListView<String> castList;


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
        System.out.println("season being initlaized");
    }

    // Setter methods to pass the data from ShowOverviewController
    public void loadSeasonData(Season season) throws IOException {
        System.out.println("we made it inside season");


        if (season != null) {
            populateCast(season);

        }
    }
    public void populateCast(Season season) throws IOException {
      //  List<String> mainCast = apIclient.fetchCastForSeasonOrEpisode(season.getSeasonNumber());
      //  ObservableList<String> observableMainCastList = FXCollections.observableArrayList(mainCast);
     //   castList.setItems(observableMainCastList);
    }
}
