package edu.metrostate;



public class Review implements Likable {
    //variables
    private MediaType type;
    private String reviewText;
    private double stars;
    private int reviewId;

    //constructor
    public Review(String text, double stars, MediaType type){
            this.reviewText = text;
            this.stars = stars;
            this.type = type;
    }

    //methods
    public MediaType getType() {
        return type;
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

    public void editReviewStars(double newStars){
        this.stars = newStars;
    }

    public double getStars() {
        return stars;
    }

}//end Class

