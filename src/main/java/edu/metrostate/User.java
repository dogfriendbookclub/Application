package edu.metrostate;

import java.util.List;

public class User {

    String userHandle;
    private String password;
    int userId;
    List<Reviewable> reviews;
    List<Likable> likes;
    List<Review> bookmarks;
    List<Reviewable> saved;

    public User(String handle, String password, int userId) {
        this.userHandle = handle;
        this.password = password;
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

    public List<Reviewable> getReviews() {
        return reviews;
    }

    public List<Likable> getLikes() {
        return likes;
    }

    public List<Review> getBookmarks() {
        return bookmarks;
    }

    public List<Reviewable> getSaved() {
        return saved;
    }
}