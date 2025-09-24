-- Drop tables in the correct order to avoid foreign key issues
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS publisher (
    publisherId INT PRIMARY KEY AUTO_INCREMENT,
    publisherName varchar(255)
);

CREATE TABLE IF NOT EXISTS author (
  authorId INT PRIMARY KEY AUTO_INCREMENT,
  authorName varchar(255)
);

CREATE TABLE IF NOT EXISTS book(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    imageUrl varchar(255),
    publisherId INT,
    FOREIGN KEY (publisherId) REFERENCES publisher(publisherId)
);

CREATE TABLE IF NOT EXISTS book_author (
    bookId INT,
    authorId INT,
    PRIMARY KEY (bookId, authorId),
    FOREIGN KEY (bookId) REFERENCES Book(id),
    FOREIGN KEY (authorId) REFERENCES Author(authorId)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);