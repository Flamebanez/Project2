import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Map;

public class ProductManager {
    private ArrayList<Product> products = new ArrayList<>();

    // Helper Methods
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }

    private String sanitizeString(String value) {
        return value == null || value.trim().isEmpty() ? "Unknown" : value.trim();
    }

    // Main Method to Load Products
    public void loadProducts(String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;

    while ((line = reader.readLine()) != null) {
        line = line.trim(); // Remove leading and trailing whitespace
        if (line.isEmpty()) {
            continue; // Skip empty lines
        }

        String[] data = line.split(",");

        // Ensure the line has enough fields to process
        if (data.length < 8) { // Minimum fields needed for any product type
            System.err.println("Skipping malformed line: " + line);
            continue;
        }

        int id = parseIntOrDefault(data[0], -1);
        String type = sanitizeString(data[1]);

        switch (type) {
            case "Movie":
                products.add(new Movie(
                    id,
                    sanitizeString(data[2]),
                    sanitizeString(data[3]),
                    sanitizeString(data[4]),
                    parseIntOrDefault(data[5], 0),
                    sanitizeString(data[6]),
                    parseIntOrDefault(data[7], 0)
                ));
                break;

            case "TV Show":
                products.add(new TVShow(
                    id,
                    sanitizeString(data[2]),
                    sanitizeString(data[3]),
                    sanitizeString(data[4]),
                    parseIntOrDefault(data[5], 0),
                    sanitizeString(data[6]),
                    parseIntOrDefault(data[7].replaceAll("[^0-9]", ""), 0)
                ));
                break;

            case "Video Game":
                products.add(new VideoGame(
                    id,
                    sanitizeString(data[2]),
                    sanitizeString(data[3]),
                    parseIntOrDefault(data[4], 0),
                    sanitizeString(data[5]),
                    sanitizeString(data[6]),
                    parseDoubleOrDefault(data[7], 0.0)
                ));
                break;

            case "Music Album":
                products.add(new MusicAlbum(
                    id,
                    parseIntOrDefault(data[2], 0),
                    sanitizeString(data[3]),
                    sanitizeString(data[4]),
                    parseDoubleOrDefault(data[5], 0.0),
                    parseIntOrDefault(data[6], 0),
                    parseDoubleOrDefault(data[7], 0.0),
                    sanitizeString(data[8])
                ));
                break;

            default:
                System.err.println("Unknown product type: " + type);
                break;
        }
    }
    reader.close();
}


    public int getTotalProducts() {
        return products.size();
    }

    public int countMovies() {
        return (int) products.stream().filter(p -> p instanceof Movie).count();
    }

    public int countTVShows() {
        return (int) products.stream().filter(p -> p instanceof TVShow).count();
    }

    public int countVideoGames() {
        return (int) products.stream().filter(p -> p instanceof VideoGame).count();
    }

    public int countMusicAlbums() {
        return (int) products.stream().filter(p -> p instanceof MusicAlbum).count();
    }

    public Product getOldestProduct() {
        return products.stream().min(Comparator.comparing(Product::getReleaseYear)).orElse(null);
    }

    public MusicAlbum getMostPopularMusicAlbum() {
        return (MusicAlbum) products.stream()
                .filter(p -> p instanceof MusicAlbum)
                .max(Comparator.comparing(p -> ((MusicAlbum) p).getGlobalSales()))
                .orElse(null);
    }

    public VideoGame getMostPopularVideoGame() {
        return (VideoGame) products.stream()
                .filter(p -> p instanceof VideoGame)
                .max(Comparator.comparing(p -> ((VideoGame) p).getCopiesSold()))
                .orElse(null);
    }

    public String getMostCommonAgeRating() {
        return products.stream()
                .filter(p -> p instanceof Movie || p instanceof TVShow)
                .map(p -> ((p instanceof Movie) ? ((Movie) p).getRating() : ((TVShow) p).getRating()))
                .collect(Collectors.groupingBy(r -> r, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    public Movie getShortestMovie() {
        return (Movie) products.stream()
                .filter(p -> p instanceof Movie)
                .min(Comparator.comparing(p -> ((Movie) p).getDuration()))
                .orElse(null);
    }

    public MusicAlbum getShortestMusicAlbum() {
        return (MusicAlbum) products.stream()
                .filter(p -> p instanceof MusicAlbum)
                .min(Comparator.comparing(p -> ((MusicAlbum) p).getDuration()))
                .orElse(null);
    }
}
