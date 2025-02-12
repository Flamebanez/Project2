public class VideoGame extends Product {
    private String platform;
    private String genre;
    private String publisher;
    private double copiesSold;

    public VideoGame(int id, String title, String platform, int releaseYear, String genre, String publisher, double copiesSold) {
        super(id, title, releaseYear);
        this.platform = platform;
        this.genre = genre;
        this.publisher = publisher;
        this.copiesSold = copiesSold;
    }

    public double getCopiesSold() {
        return copiesSold;
    }
}
