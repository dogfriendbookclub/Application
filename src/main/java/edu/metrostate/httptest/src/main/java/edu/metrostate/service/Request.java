package edu.metrostate.service;

import com.fasterxml.jackson.core.type.TypeReference;

public class Request<Req, Res> {
    private HttpMethod method;

    private String path;

    private TypeReference<Res> responseType;

    private Req requestBody;

    public Request(HttpMethod method, String path, TypeReference<Res> responseType) {
        this.method = method;
        this.path = path;
        this.responseType = responseType;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public TypeReference<Res> getResponseType() {
        return responseType;
    }

    public Req getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Req requestBody) {
        this.requestBody = requestBody;
    }
}
