package edu.metrostate.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.metrostate.models.Joke;
import edu.metrostate.service.HttpMethod;
import edu.metrostate.service.Request;

public class JokeRequest extends Request<Void, Joke> {

    public JokeRequest(Integer id) {
        super(HttpMethod.GET, "/jokes/" + id.toString(), new TypeReference<>(){});
    }
}
