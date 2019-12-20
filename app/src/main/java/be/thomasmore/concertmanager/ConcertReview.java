package be.thomasmore.concertmanager;

import java.util.List;

public class ConcertReview {
    public int id;
    public String concertID;
    public int reviewID;

    public ConcertReview() {
    }

    public ConcertReview(int id, String concertID, int reviewID ) {
        this.id = id;
        this.concertID = concertID;
        this.reviewID = reviewID;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcertID() {
        return concertID;
    }

    public void setConcertID(String concertID) {
        this.concertID = concertID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }
}
