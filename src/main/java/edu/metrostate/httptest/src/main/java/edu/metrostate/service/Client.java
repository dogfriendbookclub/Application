package edu.metrostate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    private HttpClient httpClient = HttpClient.newHttpClient();

    public class InvalidRequestException extends RuntimeException {}

    public class InvalidResponseException extends RuntimeException {}

    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl;

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public <Req, Res> Res doRequest(Request<Req, Res> request) throws InvalidRequestException {
        String urlString = baseUrl + request.getPath();
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException ex) {
            throw new InvalidRequestException();
        }
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri);
        switch (request.getMethod()) {
            case GET -> builder.GET();
            case PUT -> {
                HttpRequest.BodyPublisher publisher = createRequestBody(request);
                builder.PUT(publisher);
            }
            case POST -> {
                HttpRequest.BodyPublisher publisher = createRequestBody(request);
                builder.POST(publisher);
            }
            case DELETE -> {
                builder.DELETE();
            }
        }
        try {
            HttpResponse<InputStream> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofInputStream());
            return (Res) mapper.readValue(response.body(), request.getResponseType());
        } catch (IOException ex) {
            throw new InvalidResponseException();
        } catch (InterruptedException ex) {
            throw new InvalidResponseException();
        }
    }

    private <Req, Res> HttpRequest.BodyPublisher createRequestBody(Request<Req, Res> request) throws InvalidRequestException {
        try {
            String body = mapper.writeValueAsString(request.getRequestBody());
            return HttpRequest.BodyPublishers.ofString(body);
        } catch (JsonProcessingException ex) {
            throw new InvalidRequestException();
        }
    }
}
