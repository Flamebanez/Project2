public class Movie extends Product {
    private String director;
    private String country;
    private String rating;
    private int duration;

    public Movie(int id, String title, String director, String country, int releaseYear, String rating, int duration) {
        super(id, title, releaseYear);
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }
}
