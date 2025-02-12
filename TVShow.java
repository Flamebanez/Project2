public class TVShow extends Product {
    private String director;
    private String country;
    private String rating;
    private int numberOfSeasons;

    public TVShow(int id, String title, String director, String country, int releaseYear, String rating, int numberOfSeasons) {
        super(id, title, releaseYear);
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getRating() {
        return rating;
    }
}
