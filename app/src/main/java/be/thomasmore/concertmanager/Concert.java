package be.thomasmore.concertmanager;

import java.util.Date;
import java.util.List;

public class Concert {
    private String id;
    private String naam;
    private String url;
    private String image;
    private String date;
    private String genres;
    private int score;

    public Concert() {
    }

    public Concert(String id, String naam, String url, String image, String date, String genres) {
        this.id = id;
        this.naam = naam;
        this.url = url;
        this.image = image;
        this.date = date;
        this.genres = genres;
        this.score = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString () {
        return naam;
    }

}
