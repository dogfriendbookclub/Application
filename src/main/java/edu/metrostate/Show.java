package edu.metrostate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Show implements Reviewable, Likable {
    /*Variables, every JsonProperty is the name of the variable in the database API
    They are needed so that Jackson can map the information to the correct variables in our project */
    @JsonProperty("created_by")
    private List<Creator> creator;

    @JsonProperty("genres")
    private List <Genre> genre;

    @JsonProperty("overview")
    private String premise;

    @JsonProperty("first_air_date")
    private String yearStart;

    @JsonProperty("status")
    private String stillRunning;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("name")
    private String title;

    @JsonProperty("series_id")
    private int showID;

    @JsonProperty("seasons")
    private List<Season> seasons;

    @JsonProperty("backdrop_path")
    private String posterPath;

    private int stars;

    public Show(List<Creator> creator, List<Genre> genre, String premise, String yearStart, String stillRunning,
                int voteCount, String title, int showID, List<Season> seasons, int stars, String posterPath) {
        this.creator = creator;
        this.genre = genre;
        this.premise = premise;
        this.yearStart = yearStart;
        this.stillRunning = stillRunning;
        this.voteCount = voteCount;
        this.title = title;
        this.showID = showID;
        this.seasons = seasons;
        this.stars = stars;
        this.posterPath = posterPath;
    }
    public Show(){

    }

    public List<Creator> getCreators() {
        return creator;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public String getPremise() {
        return premise;
    }

    public String getYearStart() {
        return yearStart;
    }

    public String isStillRunning() {
        return stillRunning;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getShowId() {
        return showID;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    @Override
    public int getStars() {
        return stars;
    }

    @Override
    public void recalculateStars(Review review) {
        // specific implementation requires more information at this time.
        // something like:
        /* this.stars = (review.getStars() + (this.stars * numOfReviews) / (numOfReviews +1)
         *  numOfReviews++ */
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
