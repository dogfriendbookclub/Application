CREATE TABLE review (
    reviewId INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER,
    showId INTEGER,
    seasonId INTEGER,
    episodeId INTEGER,
    reviewText VARCHAR(),
    reviewScore INTEGER DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES user(userId)
)
