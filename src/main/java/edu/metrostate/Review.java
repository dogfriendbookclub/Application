package edu.metrostate;



public class Review implements Likable{
    //variables
    private MediaType type;
    private String reviewText;
    private double reviewStars;
    private int reviewId;
    private boolean  heart = false;

    //constructor
    public Review(String text, double stars, boolean heart){
            this.reviewText = text;
            this.reviewStars = stars;
            this.heart = heart;
    }

    //methods
    public MediaType getType() {
        return type;
    }

    public String getReviewText() {
        return reviewText;
    }

    public double getReviewStars() {
        return reviewStars;
    }

    public int getReviewId() {
        return reviewId;
    }

    /* turned this into a constructor
    public void createReview(String text, double newStars){
        this.reviewText = text;
        this.reviewStars = newStars;
    }
     */

    public void editReviewText(String newText){
            this.reviewText = newText;
    }

    public void editReviewStars(double newStars){
        this.reviewStars = newStars;
    }

    @Override
    public void addLike() {
        this.heart = true;
    }

    @Override
    public void removeLike() {
        this.heart = false;
    }
}//end Class

