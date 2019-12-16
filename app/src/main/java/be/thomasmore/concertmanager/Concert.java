package be.thomasmore.concertmanager;

public class Concert {
    private int id;
    private String naam;

    public Concert() {
    }

    public Concert(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString () {
        return naam;
    }

}
