package gui.episodeoverview;

import java.net.URL;
import java.util.ResourceBundle;
import edu.metrostate.APIclient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class EpisodeOverviewController implements Initializable {
    @FXML
    private ListView creatorsList;

    @FXML
    private ListView castList;

    @FXML
    private ListView producersList;

    @FXML
    private ListView writersList;

    @FXML
    private TextArea episodeSynopsis;

    @FXML
    private Text featureUsername;

    @FXML
    private Text reviewRate;

    @FXML
    private Text featureDate;

    @FXML
    private TextArea featurReview;

    @FXML
    private Text episodeRate;

    @FXML
    private TextField userReview;

    private APIclient apIclient = new APIclient();

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
}
