package edu.metrostate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowPreview {

    @JsonProperty ("series_id")
    private int showID;

    @JsonProperty("name")
    private String Title;

    @JsonProperty("poster_path")
    private String posterPath;

    public ShowPreview(int showID, String title, String posterPath) {
        this.showID = showID;
        Title = title;
        this.posterPath = posterPath;
    }

    public ShowPreview(){
    }

    public int getShowId() {
        return showID;
    }

    public String getTitle() {
        return Title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return "Show ID: " + showID + "\nTitle: " + Title + "\nPoster Path: " + posterPath;
    }

}
