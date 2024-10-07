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

    public Show(List<Creator> creator, List<Genre> genre, String premise, String yearStart, String stillRunning,
                int voteCount, String title, int showID, List<Season> season) {
        this.creator = creator;
        this.genre = genre;
        this.premise = premise;
        this.yearStart = yearStart;
        this.stillRunning = stillRunning;
        this.voteCount = voteCount;
        this.title = title;
        this.showID = showID;
        this.seasons = seasons;
    }
    public Show(){

    }

    public List<Creator> getCreator() {
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

    public int getShowID() {
        return showID;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    @Override
    public void addLike() {

    }

    @Override
    public void removeLike() {

    }

    @Override
    public double getStars() {
        return 0;
    }

    @Override
    public int getShowId() {
        return 0;
    }

    @Override
    public void recalculateStars(Reviewable reviewable) {

    }
}
