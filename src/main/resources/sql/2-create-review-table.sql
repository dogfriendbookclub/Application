CREATE TABLE review (
    reviewId INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER,
    showId INTEGER,
    seasonId INTEGER,
    episodeId INTEGER,
    mediaType TEXT CHECK(mediaType IN ('SHOW', 'SEASON', 'EPISODE', 'REVIEW')),
    reviewText VARCHAR(10000),
    reviewScore INTEGER DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES user(userId)
);
