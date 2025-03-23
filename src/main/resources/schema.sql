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

