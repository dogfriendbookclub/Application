package edu.metrostate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Review implements Likable {
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
    public Review(String text, int stars, MediaType mediaType){
            this.reviewText = text;
            this.stars = stars;
            this.mediaType = mediaType;
    }

    public Review(String text, int stars, String type, int reviewId){
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.reviewId = reviewId;
    }
    public Review(String text, int stars, int showId, String type){
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.showId = showId;
    }
    public Review(String text, int stars, int showId, int seasonId, String type){
        this.reviewText = text;
        this.stars = stars;
        this.mediaType = mediaType;
        this.showId = showId;
        this.seasonId = seasonId;
    }
    public Review(String text, int stars, int showId, int seasonId, int episodeId, String type){
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

}//end Class

