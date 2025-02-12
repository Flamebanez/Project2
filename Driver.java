import java.io.IOException; 

public class Driver {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        try {
            manager.loadProducts("products.csv");

            System.out.println("Total number of products: " + manager.getTotalProducts());
            System.out.println("Total number of Movies: " + manager.countMovies());
            System.out.println("Total number of TV Shows: " + manager.countTVShows());
            System.out.println("Total number of Video Games: " + manager.countVideoGames());
            System.out.println("Total number of Music Albums: " + manager.countMusicAlbums());
            System.out.println("Oldest product: " + manager.getOldestProduct().getTitle());
            System.out.println("Most popular Music Album: " + manager.getMostPopularMusicAlbum().getTitle());
            System.out.println("Most popular Video Game: " + manager.getMostPopularVideoGame().getTitle());
            System.out.println("Most common age rating: " + manager.getMostCommonAgeRating());
            System.out.println("Shortest Movie: " + manager.getShortestMovie().getTitle());
            System.out.println("Shortest Music Album: " + manager.getShortestMusicAlbum().getTitle());
        } catch (IOException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
    }
}
