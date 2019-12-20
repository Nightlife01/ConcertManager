package be.thomasmore.concertmanager;

public class Review {
    public int id;
    public int score;
    public String text;
    public int gebruikerID;

    public Review() {
    }

    public Review(int id, int score, String text, int gebruikerID) {
        this.id = id;
        this.score = score;
        this.text = text;
        this.gebruikerID = gebruikerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
    }
}
