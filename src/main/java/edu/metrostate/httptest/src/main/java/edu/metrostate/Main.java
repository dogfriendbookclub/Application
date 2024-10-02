package edu.metrostate;

import edu.metrostate.models.Joke;
import edu.metrostate.requests.JokeRequest;
import edu.metrostate.requests.RandomJokeRequest;
import edu.metrostate.requests.TenRandomJokesRequest;
import edu.metrostate.service.Client;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("https://official-joke-api.appspot.com");
        RandomJokeRequest randomJokeRequest = new RandomJokeRequest();

        Joke randomJoke = client.doRequest(randomJokeRequest);
        System.out.println("Random Joke:");
        System.out.println(randomJoke);
        System.out.println("\n");

        JokeRequest jokeRequest = new JokeRequest(randomJoke.getId());
        Joke joke = client.doRequest(jokeRequest);

        System.out.println("Joke by ID " + randomJoke.getId() + ":");
        System.out.println(joke);
        System.out.println("\n");

        TenRandomJokesRequest tenRandomJokesRequest = new TenRandomJokesRequest();

        List<Joke> jokeList = client.doRequest(tenRandomJokesRequest);

        System.out.println("Ten Random Jokes:");
        jokeList.forEach(item -> System.out.println(item));
    }
}