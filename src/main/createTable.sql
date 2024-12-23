use wiki;
CREATE TABLE IF NOT EXISTS en (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100),
  url VARCHAR(100),
  abstract TEXT,
  body_text LONGTEXT,
  body_html LONGTEXT
);