package edu.metrostate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Season implements Reviewable, Likable {
    @JsonAlias("season_number")
    private int seasonNumber;

    @JsonProperty("air_date")
    private String airdate;

    @JsonProperty("series_id")
    private int showId;

    @JsonProperty("id")
    private int seasonId;

    @JsonProperty("vote_average")
    private double stars;

    @JsonProperty("episodes")
    private List<Episode> episodes = new ArrayList<>(); // Initialize with an empty list

    @JsonProperty("episode_count")
    private int episodeCount;

    // Default constructor
    public Season() {
    }

    // Parameterized constructor
    public Season(int seasonNumber, String airdate, int showId, int seasonId, double stars,
                  List<Episode> episodes, int episodeCount)  {
        this.seasonNumber = seasonNumber;
        this.airdate = airdate;
        this.showId = showId;
        this.seasonId = seasonId;
        this.stars = stars;
        this.episodes = episodes != null ? episodes : new ArrayList<>(); // Ensure episodes is not null
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return seasonNumber + 1;
    }

    public String getAirdate() {
        return airdate;
    }

    @Override
    public double getStars() {
        return stars;
    }

    @Override
    public int getShowId() {
        return showId;
    }

    @Override
    public void recalculateStars(Review review) {
        // specific implementation requires more informatiion at this time.
        // something like:
        /* this.stars = (review.getStars() + (this.stars * numOfReviews) / (numOfReviews +1)
         *  numOfReviews++ */
    }

    public int getSeasonId() {
        return seasonId;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }
}
