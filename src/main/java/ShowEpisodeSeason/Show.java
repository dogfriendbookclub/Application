import java.util.List;

public class Show implements Media{
    private int showid;
    private String title;
    private String creator;
    private List<Season> seasons;
    private String genre;
    private boolean stillRunning;

    public Show(int showid, String title, String creator, List<Season> seasons, String genre, boolean stillRunning) {
        this.showid = showid;
        this.title = title;
        this.creator = creator;
        this.seasons = seasons;
        this.genre = genre;
        this.stillRunning = stillRunning;
    }

    public Show() {

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

    @Override
    public String toString() {
        return "Show{" +
                "id=" + showid +
                ", title='" + title + '\'' +
                ", creator='" + creator + '\'' +
                ", seasons=" + seasons +
                '}';
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
