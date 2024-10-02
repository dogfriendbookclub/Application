package edu.metrostate.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.metrostate.models.Joke;
import edu.metrostate.service.HttpMethod;
import edu.metrostate.service.Request;

public class RandomJokeRequest extends Request<Void, Joke> {

    public RandomJokeRequest() {
        super(HttpMethod.GET, "/jokes/random", new TypeReference<>(){});
    }
}
