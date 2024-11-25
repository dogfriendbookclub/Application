package edu.metrostate;

public interface Reviewable {
    //"public" declaration is not needed as methods in interfaces are understood to be public

    int getStars();
    int getShowId();
    void recalculateStars(Review review);
}
