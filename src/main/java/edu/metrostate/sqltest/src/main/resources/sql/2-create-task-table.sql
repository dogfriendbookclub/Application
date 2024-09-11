CREATE TABLE task (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT,
  person_id INTEGER,
  completed INTEGER DEFAULT 0,
  FOREIGN KEY(person_id) REFERENCES person(id)
 );