import java.util.List;

public class Season implements Media{
    private int seasonNum;
    private int seasonYearStart;
    private int seasonYearEnd;
    private double seasonStars;
    private int showId;
    private int seasonId;
    private List<Episode> episodeList;
    private String synopsis;

    public Season(int seasonNum, int seasonYearStart, int seasonYearEnd, double seasonStars, int showId, int seasonId, List<Episode> episodeList, String synopsis) {
        this.seasonNum = seasonNum;
        this.seasonYearStart = seasonYearStart;
        this.seasonYearEnd = seasonYearEnd;
        this.seasonStars = seasonStars;
        this.showId = showId;
        this.seasonId = seasonId;
        this.episodeList = episodeList;
        this.synopsis = synopsis;
    }

    public Season() {

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

    public int getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    public int getSeasonYearStart() {
        return seasonYearStart;
    }

    public void setSeasonYearStart(int seasonYearStart) {
        this.seasonYearStart = seasonYearStart;
    }

    public int getSeasonYearEnd() {
        return seasonYearEnd;
    }

    public void setSeasonYearEnd(int seasonYearEnd) {
        this.seasonYearEnd = seasonYearEnd;
    }

    public double getSeasonStars() {
        return seasonStars;
    }

    public void setSeasonStars(double seasonStars) {
        this.seasonStars = seasonStars;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public String toString() {
        return "Season{" +
                "id=" + seasonId +
                ", seasonNumber=" + seasonNum +
                ", startYear=" + seasonYearStart +
                ", endYear=" + seasonYearEnd +
                ", episodes=" +  episodeList +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }


}
