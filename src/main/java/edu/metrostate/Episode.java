package edu.metrostate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode implements Reviewable, Likable {
    private String synopsis;
    @JsonProperty("name")
    private String episodeName;

    @JsonProperty("episode_number")
    private int episodeNum;


    private int runtime;

    @JsonProperty("series_id")
    private int showId;

    @JsonProperty("season_number")
    private int seasonId;

    @JsonProperty("id")
    private int episodeId;

    @JsonProperty("vote_count")
    private int episodeStars;

    public Episode(String synopsis, String episodeName, int episodeNum, int runtime,
                   int showId, int seasonId, int episodeId, int episodeStars)  {
        this.synopsis = synopsis;
        this.episodeName = episodeName;
        this.episodeNum = episodeNum;
        this.runtime = runtime;
        this.showId = showId;
        this.seasonId = seasonId;
        this.episodeId = episodeId;
        this.episodeStars = episodeStars;
    }

    public Episode() {
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public int getRuntime() {
        return runtime;
    }

    @Override
    public double getStars() {
        return 0;
    }

    public int getShowId() {
        return showId;
    }

    @Override
    public void recalculateStars(Reviewable reviewable) {

    }

    public int getSeasonId() {
        return seasonId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public int getEpisodeStars() {
        return episodeStars;
    }

    @Override
    public void addLike() {

    }

    @Override
    public void removeLike() {

    }
}
