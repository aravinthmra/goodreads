-- Drop tables in the correct order to avoid foreign key issues
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS users;

-- ======================
-- Publisher Table
-- ======================
CREATE TABLE IF NOT EXISTS publisher (
    publisher_id SERIAL PRIMARY KEY,
    publisher_name VARCHAR(255)
);

-- ======================
-- Author Table
-- ======================
CREATE TABLE IF NOT EXISTS author (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR(255)
);

-- ======================
-- Book Table
-- ======================
CREATE TABLE IF NOT EXISTS book(
    book_id SERIAL PRIMARY KEY,
    book_name VARCHAR(255),
    image_url VARCHAR(255),
    publisher_id INT,
    CONSTRAINT fk_book_publisher FOREIGN KEY (publisher_id)
        REFERENCES publisher(publisher_id)
        ON DELETE SET NULL
);

-- ======================
-- Book_Author Table (Many-to-Many)
-- ======================
CREATE TABLE IF NOT EXISTS book_author (
    book_id INT,
    author_id INT,
    PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_author_book FOREIGN KEY (book_id)
        REFERENCES book(book_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_book_author_author FOREIGN KEY (author_id)
        REFERENCES author(author_id)
        ON DELETE CASCADE
);

-- ======================
-- Users Table
-- ======================
CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);