package edu.metrostate.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.metrostate.models.Joke;
import edu.metrostate.service.HttpMethod;
import edu.metrostate.service.Request;

import java.util.List;

public class TenRandomJokesRequest extends Request<Void, List<Joke>> {

    public TenRandomJokesRequest() {
        super(HttpMethod.GET, "/jokes/ten", new TypeReference<>(){});
    }
}
