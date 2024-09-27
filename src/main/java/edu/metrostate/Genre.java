package edu.metrostate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {
    private int id;
    private String name;

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
