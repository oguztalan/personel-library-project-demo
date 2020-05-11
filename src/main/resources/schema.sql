DROP TABLE IF EXISTS TBL_BOOKS;

CREATE TABLE TBL_BOOKS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  author_name VARCHAR(250) NOT NULL,
  book_name VARCHAR(250) NOT NULL,
  release_year VARCHAR(15) DEFAULT NULL
);
