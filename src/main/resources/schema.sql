CREATE TABLE IF NOT EXISTS book(
id INT PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
imageUrl varchar(255)
);

CREATE TABLE IF NOT EXISTS publisher (
publisherId INT PRIMARY KEY AUTO_INCREMENT,
publisherName varchar(255)
);