package edu.metrostate;



public class Review implements Likable{
    //variables
    private MediaType type;
    private String reviewText;
    private double reviewStars;
    private int reviewId;

    //get showID and turn it into getID or not

    //constructor
    public Review(String text, double stars){
            this.reviewText = text;
            this.reviewStars = stars;
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

    /*
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
        System.out.println("add like");
    }

    @Override
    public void removeLike() {
        System.out.println("remove like");
    }
}//end Class

