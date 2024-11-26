package edu.metrostate;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Review extends dbSqlModel implements Likable {
    //variables
    private String reviewText;
    private double stars;
    private int reviewId;
    private int userId;
    private int showId;
    private int seasonId;
    private int episodeId;
    private MediaType mediaType;

    //constructor
    public Review(String text, double stars, MediaType mediaType){
            super(null);
            this.reviewText = text;
            this.stars = stars;
            this.mediaType = mediaType;
    }

    public Review(String text, int stars, int reviewId){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.reviewId = reviewId;
    }

    public Review(String text, int stars, int showId, int reviewId){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.showId = showId;
        this.reviewId = reviewId;
    }

    public Review(String text, int stars, MediaType mediaType, int reviewId){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.reviewId = reviewId;
    }
    public Review(String text, int stars, int showId, MediaType mediaType){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.showId = showId;
    }
    public Review(String text, int stars, int showId, int seasonId, MediaType mediaType){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.showId = showId;
        this.seasonId = seasonId;
    }
    public Review(String text, int stars, int showId, int seasonId, int episodeId, MediaType mediaType){
        super(null);
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.showId = showId;
        this.seasonId = seasonId;
        this.episodeId = episodeId;
    }

    //methods
    public MediaType getType() {
        return mediaType;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void editReviewText(String newText){
            this.reviewText = newText;
    }

    public void editReviewStars(int newStars){
        this.stars = newStars;
    }

    public double getStars() {
        return stars;
    }

    public int getShowId() {
        return showId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }


    public PreparedStatement insertStatement(Connection connection) throws SQLException {
        String sql = "INSERT INTO review (userId, showId, seasonId, episodeId, mediaType, reviewText, reviewScore) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING reviewId;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, this.userId);
        statement.setObject(2, this.showId); // Nullable field, use setObject
        statement.setObject(3, this.seasonId); // Nullable field, use setObject
        statement.setObject(4, this.episodeId); // Nullable field, use setObject
        statement.setString(5, MediaType.toDatabaseValue(this.mediaType));
        statement.setString(6, this.reviewText);
        statement.setDouble(7, this.stars);
        return statement;
    }


    public PreparedStatement updateStatement(Connection connection) throws SQLException {
        String sql = "UPDATE review SET userId = ?, showId = ?, seasonId = ?, episodeId = ?, " +
                "mediaType = ?, reviewText = ?, reviewScore = ? WHERE reviewId = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, this.userId);
        statement.setObject(2, this.showId); // Nullable field, use setObject
        statement.setObject(3, this.seasonId); // Nullable field, use setObject
        statement.setObject(4, this.episodeId); // Nullable field, use setObject
        statement.setString(5, MediaType.toDatabaseValue(this.mediaType));
        statement.setString(6, this.reviewText);
        statement.setDouble(7, this.stars);
        statement.setInt(8, this.reviewId);
        return statement;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("toString called...");
        str.append("\ntext: " + this.getReviewText());
        str.append("\nstars: " + this.getStars());
        str.append("\nshowId: " + this.getShowId());
        str.append("\nreviewId: " + this.getReviewId());
        return str.toString();
    }

    public static List<Review> loadAll(Connection connection) {
        List<Review> reviewList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM review");
            while (resultSet.next()) {
                String text = resultSet.getString("reviewText");
                Integer score = resultSet.getInt("reviewScore");
                Integer showId = resultSet.getInt("showId");
                Integer reviewId = resultSet.getInt("reviewId");


                Review review = new Review(text, score, showId, reviewId);
                reviewList.add(review);
            }
            dbUtil.closeQuietly(resultSet);
            dbUtil.closeQuietly(statement);
        } catch (SQLException ex) {

        }
        return reviewList;
    }

}//end Class

