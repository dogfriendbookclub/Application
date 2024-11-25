package edu.metrostate;

public enum MediaType {
    SHOW,
    SEASON,
    EPISODE,
    REVIEW;

    public static String toDatabaseValue(MediaType mediaType) {
        return mediaType != null ? mediaType.name() : null;
    }
}
