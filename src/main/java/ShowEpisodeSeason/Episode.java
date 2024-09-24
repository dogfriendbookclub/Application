import java.util.List;

public class Episode implements Media{
    private String title;
    private String cast;
    private String director;
    private String synopsis;
    private String episodeName;
    private  int runTime;
    private int seasonId;
    private int airDate;
    private int episodeId;
    private double episodeStars;


    public Episode(String title, String cast, String director, String synopsis, String episodeName, int runTime, int seasonId, int airDate, int episodeId, double episodeStars) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.synopsis = synopsis;
        this.episodeName = episodeName;
        this.runTime = runTime;
        this.seasonId = seasonId;
        this.airDate = airDate;
        this.episodeId = episodeId;
        this.episodeStars = episodeStars;
    }

    public Episode() {

    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSynopsis() {
        return "";
    }

    @Override
    public void setSynopsis(String synopsis) {

    }

    @Override
    public int getRunTime() {
        return 0;
    }

    @Override
    public void setRunTime(int runtime) {

    }

    @Override
    public double calculateRating() {
        return 0;
    }

    @Override
    public void setRating(double rating) {

    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getAirDate() {
        return airDate;
    }

    public void setAirDate(int airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public double getEpisodeStars() {
        return episodeStars;
    }

    public void setEpisodeStars(double episodeStars) {
        this.episodeStars = episodeStars;
    }
    public String toString() {
        return "Episode{" +
                ", title='" + title + '\'' +
                ", cast='" + cast + '\'' +
                ", director='" + director + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", episodeName='" + episodeName + '\'' +
                ", runtime=" + runTime +
                ", seasonId=" + seasonId +
                ", airDate=" + airDate +
                ", episodeId=" + episodeId +
                '}';
    }

}
