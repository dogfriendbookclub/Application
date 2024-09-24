//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Episode episode1 = new Episode();
        episode1.setEpisodeId(1);
        episode1.setTitle("Pilot");
        episode1.setCast("John Doe, Jane Smith");
        episode1.setDirector("Alice Johnson");
        episode1.setSynopsis("The first episode of the series.");
        episode1.setEpisodeName("Pilot");
        episode1.setRunTime(45);
        episode1.setSeasonId(1);
        episode1.setAirDate(20230101);
        episode1.setEpisodeId(101);

        Episode episode2 = new Episode();
        episode2.setEpisodeId(2);
        episode2.setTitle("Second Episode");
        episode2.setCast("John Doe, Jane Smith");
        episode2.setDirector("Alice Johnson");
        episode2.setSynopsis("The second episode of the series.");
        episode2.setEpisodeName("Second Episode");
        episode2.setRunTime(50);
        episode2.setSeasonId(1);
        episode2.setAirDate(20230108);
        episode2.setEpisodeId(102);

        // Create a season
        Season season1 = new Season();
        season1.setSeasonId(1);
        season1.setSeasonNum(1);
        season1.setSeasonYearStart(2023);
        season1.setSeasonYearEnd(2023);
        season1.setEpisodeList(Arrays.asList(episode1, episode2));
        season1.setSynopsis("The first season of the series.");

        // Create a show
        Show show = new Show();
        show.setShowid(1);
        show.setTitle("My Awesome Show");
        show.setCreator("John Doe");
        show.setSeasons(Arrays.asList(season1));

        // Print details
        System.out.println(show);
        System.out.println(season1);
        System.out.println(episode1);
        System.out.println(episode2);
    }
}