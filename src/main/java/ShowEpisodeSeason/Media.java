public interface Media {
    String getTitle();
    void setTitle(String title);

    String getSynopsis();
    void setSynopsis(String synopsis);

    int getRunTime();
    void setRunTime(int runtime);

    double calculateRating();
    void setRating(double rating);
}
