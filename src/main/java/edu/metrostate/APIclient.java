package edu.metrostate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class APIclient {
    private static final String API_KEY = "1ad22b3bd7a42e3bd60bee3f9610940f";
    private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    //constructor
    public APIclient() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }


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

}//end APIclient class


