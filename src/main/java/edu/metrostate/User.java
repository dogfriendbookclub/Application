package edu.metrostate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User extends dbSqlModel {

    String userHandle;
    private String password;
    int userId;
    List<Review> reviews;
    List<Likable> likes;
    List<Review> bookmarks;
    List<Reviewable> saved;
    List<Review> reactions;

    public User(String handle) {
        super(null);
        this.userHandle = handle;
        //this.password = password;
        Random rand = new Random();
        this.userId = rand.nextInt(1000);
        System.out.println("user " + userHandle + " created with userId " + userId);
    }

    public User(String handle, int userId, int id) {
        super(id);
        this.userHandle = handle;
        //this.password = password;
        this.userId = userId;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String s) {
        userHandle = s;
    }

    private void resetPassword(String s) {
        password = s;
    }

    public int getUserId() {
        return userId;
    }

    public List<Review> getReviews(Connection connection) {
        List<Review> reviewList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM task WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            Boolean result = statement.execute();
            if (result) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    Integer reviewId = resultSet.getInt("reviewId");
                    String reviewText = resultSet.getString("reviewText");
                    Integer reviewScore = resultSet.getInt("reviewScore");
                    String mediaType = resultSet.getString("mediaType");
                    Review review = new Review(reviewText, reviewScore, mediaType, reviewId);
                    review.setReviewId(review.getReviewId());
                    reviewList.add(review);
                }
                dbUtil.closeQuietly(resultSet);
            }
            dbUtil.closeQuietly(statement);
        } catch (SQLException ex) {

        }
        return reviewList;
    }

    public List<Likable> getLikes(Connection connection) {
        return likes;
    }

    public List<Review> getBookmarks() {
        return bookmarks;
    }

    public List<Reviewable> getSaved() {
        return saved;
    }

    public void addLike(Likable likable) {
        likes.add(likable);
    }

    public void removeLike(Likable likable) {
        likes.remove(likable);
    }

    public void addReaction(Review review) {
        reactions.add(review);
    }

    public void removeReaction(Review review) {
        reactions.remove(review);
    }

    public List<Review> getReactions() {
        return bookmarks;
    }

    @Override
    public PreparedStatement insertStatement(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement updateStatement(Connection connection) throws SQLException {
        return null;
    }
}
