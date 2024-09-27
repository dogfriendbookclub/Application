package edu.metrostate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creator {
    private int id;
    private String creditId;
    private String name;

    public Creator(int id, @JsonAlias("credit_id") String creditId, String name) {
        this.id = id;
        this.creditId = creditId;
        this.name = name;
    }

    public Creator(){

    }

    public int getId() {
        return id;
    }


    public String getCreditId() {
        return creditId;
    }

    public String getName() {
        return name;
    }

}

