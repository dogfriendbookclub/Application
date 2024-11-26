package edu.metrostate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class APIclient {
    private static final String API_KEY = "1ad22b3bd7a42e3bd60bee3f9610940f";
    private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private static final String SEARCH_URL = "https://api.themoviedb.org/3/search/tv";


    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    //constructor
    public APIclient() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    //This method is a call that will fetch the data of a show, need to work on Episodes
    public Show fetchShowData(int showId) throws IOException {
        String url = BASE_URL + showId + "?api_key=" + API_KEY + "&append_to_response=seasons";
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            System.out.println("JSON Response: " + jsonResponse); // Debug print

            return objectMapper.readValue(jsonResponse, Show.class);
        }
    }

    public void fetchEpisodes(Season season) throws IOException {
        String url = BASE_URL + season.getShowId() + "/season/" + season.getSeasonNumber() + "?api_key=" + API_KEY;
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                System.out.println("Request URL: " + url);
                System.out.println("Show ID: " + season.getShowId());
                System.out.println("Season Number: " + season.getSeasonNumber());
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            System.out.println("JSON Response: " + jsonResponse); // Debug print

            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode episodesNode = rootNode.path("episodes");

            if (episodesNode.isArray()) {
                for (JsonNode episodeNode : episodesNode) {
                    Episode episode = new Episode(
                            episodeNode.path("overview").asText(),
                            episodeNode.path("name").asText(),
                            episodeNode.path("episode_number").asInt(),
                            episodeNode.path("runtime").asInt(0),
                            season.getShowId(),
                            season.getSeasonNumber(),
                            episodeNode.path("id").asInt(),
                            episodeNode.path("vote_average").asInt(0)
                    );

                    season.getEpisodes().add(episode);
                }
            } else {
                System.err.println("No episodes found for season: " + season.getSeasonNumber());
            }
        }

    }

    public List<String> fetchMainCast(int showId) throws IOException {
        String url = BASE_URL + showId + "/credits?api_key=" + API_KEY; // Use the correct credits endpoint
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Extract the "cast" array (this contains the main cast members)
            JsonNode castNode = rootNode.path("cast");

            List<String> castNames = new ArrayList<>();

            // Loop through the cast array and extract the name of each actor/actress
            for (JsonNode castMember : castNode) {
                String name = castMember.path("name").asText();
                castNames.add(name);
            }

            return castNames;
        }
    }

    //Method to fetch a list of popular shows that is then used on the homepage for trending TV shows on display.
    public List<ShowPreview> fetchPopularShows() throws IOException {
        String url = BASE_URL + "popular?api_key=" + API_KEY;
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Extract the "results" array from the JSON response
            JsonNode resultsNode = rootNode.path("results");

            // Create a list to store ShowPreview objects
            List<ShowPreview> showPreviews = new ArrayList<>();

            // Loop through the results and extract showID and posterPath
            for (JsonNode node : resultsNode) {
                //If list is already at 15, break
                if(showPreviews.size() >= 15){
                    break;
                }
                int showID = node.path("id").asInt();
                String title = node.path("name").asText();
                String posterPath = node.path("poster_path").asText();

                // Add the ShowPreview to the list
                showPreviews.add(new ShowPreview(showID, title, posterPath));
            }

            return showPreviews; // Return the list of ShowPreview
        }
    }
    public List<String> fetchCastForSeason(Season season) throws IOException {
        String url = BASE_URL + season.getShowId() + "/season/" + season.getSeasonNumber() + "/credits?api_key=" + API_KEY;
        System.out.println("Fetching cast for URL: " + url);  // Debugging

        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Extract the "cast" array (this contains the cast members for the season)
            JsonNode castNode = rootNode.path("cast");

            List<String> castNames = new ArrayList<>();

            // Loop through the cast array and extract the name of each actor/actress
            for (JsonNode castMember : castNode) {
                String name = castMember.path("name").asText();  // Get the name of the cast member
                castNames.add(name);  // Add it to the list
            }

            return castNames;
        }
    }
    public List<String> fetchCastForEpisode(int showId, int seasonNumber, int episodeNumber) throws IOException {
        String url = "https://api.themoviedb.org/3/tv/" + showId +
                "/season/" + seasonNumber +
                "/episode/" + episodeNumber +
                "?api_key=" + API_KEY;

        System.out.println("Fetching episode details from URL: " + url); // Log the URL

        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() == 404) {
                System.out.println("Episode not found (404). Check the URL parameters.");
                return Collections.emptyList();  // Return empty list or handle this case
            }

            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Extract guest stars
            JsonNode guestStarsNode = rootNode.path("guest_stars");
            List<String> guestStarNames = new ArrayList<>();
            for (JsonNode guestStar : guestStarsNode) {
                String name = guestStar.path("name").asText();
                guestStarNames.add(name);
            }

            return guestStarNames;
        }
    }





    public List<ShowPreview> fetchSearchResults(String searchQuery) throws IOException {
        final int NUMRESULTS = 5;

        String url = SEARCH_URL + "?query=" + searchQuery + "&language=en-US&api_key=" + API_KEY;
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                throw new IOException("Unexpected response code: " + response.getCode());
            }

            String jsonResponse = new String(response.getEntity().getContent().readAllBytes());
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Extract the "results" array from the JSON response
            JsonNode resultsNode = rootNode.path("results");

            // Create a list to store ShowPreview objects
            List<ShowPreview> searchResults = new ArrayList<>();

            // Loop through the results and extract showID and posterPath
            for (JsonNode node : resultsNode) {
                // display no more than 5 search results

                if(searchResults.size() >= NUMRESULTS){

                    break;
                }
                int showID = node.path("id").asInt();
                String title = node.path("name").asText();
                String posterPath = node.path("poster_path").asText();

                // Add the ShowPreview to the list
                searchResults.add(new ShowPreview(showID, title, posterPath));
            }

            return searchResults; // Return the list of ShowPreview

        }

    }









}
