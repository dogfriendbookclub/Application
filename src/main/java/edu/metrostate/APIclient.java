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
        String url = BASE_URL + showId + "?api_key=" + API_KEY + "&append_to_response=seasons,episodes";
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
