public class MusicAlbum extends Product {
    private String artist;
    private double globalSales;
    private int tracks;
    private double duration;
    private String genre;

    public MusicAlbum(int id, int releaseYear, String artist, String title, double globalSales, int tracks, double duration, String genre) {
        super(id, title, releaseYear);
        this.artist = artist;
        this.globalSales = globalSales;
        this.tracks = tracks;
        this.duration = duration;
        this.genre = genre;
    }

    public double getGlobalSales() {
        return globalSales;
    }

    public double getDuration() {
        return duration;
    }
}
