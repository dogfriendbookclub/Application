package edu.metrostate;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        APIclient client = new APIclient();
        int showId = 1396; // Replace with the actual show ID
        int seasonNumberToPrint = 3; // Specify the season number you want to print

        try {
            Show show = client.fetchShowData(showId);
            System.out.println("Show: " + show.getTitle());
            System.out.println("Status: " + show.isStillRunning());

            for (Season season : show.getSeasons()) {
                if (season.getSeasonNumber() == seasonNumberToPrint) {
                    System.out.println("Season " + season.getSeasonNumber() + ": " + season.getAirdate());
                    System.out.println("Number of Episodes: " + season.getEpisodeCount());
                    for (Episode episode : season.getEpisodes()) {
                        System.out.println("  Episode " + episode.getEpisodeNum() + ": " + episode.getEpisodeName());
                        System.out.println("    Synopsis: " + episode.getSynopsis());
                        System.out.println("    Runtime: " + episode.getRuntime() + " minutes");
                        System.out.println("    Vote Count: " + episode.getEpisodeStars());
                    }
                    break; // Exit the loop once the desired season is found and printed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}