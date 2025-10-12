-- ======================
-- Publishers
-- ======================
INSERT INTO publisher (publisher_name) VALUES ('Bloomsbury Publishing');
INSERT INTO publisher (publisher_name) VALUES ('Rupa Publications');
INSERT INTO publisher (publisher_name) VALUES ('Knopf Canada');
INSERT INTO publisher (publisher_name) VALUES ('Simon & Schuster');
INSERT INTO publisher (publisher_name) VALUES ('Penguin Random House');
INSERT INTO publisher (publisher_name) VALUES ('Viking Press');

-- ======================
-- Authors
-- ======================
INSERT INTO author (author_name) VALUES ('J.K.Rowling');
INSERT INTO author (author_name) VALUES ('Larry Niven');
INSERT INTO author (author_name) VALUES ('Jerry Pournelle');
INSERT INTO author (author_name) VALUES ('Stephen King');
INSERT INTO author (author_name) VALUES ('Peter Straub');
INSERT INTO author (author_name) VALUES ('Chetan Bhagat');
INSERT INTO author (author_name) VALUES ('Rhonda Byrne');

-- ======================
-- Books
-- ======================
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('Harry Potter and the Sorcerer''s Stone', 'harry_potter_and_the_sorcerers_stone.jpg', 1);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('The Mote in Gods Eye', 'mote_in_gods_eye.jpg', 3);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('The Talisman', 'the_talisman.jpg', 1);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('The Stand', 'the_stand.jpg', 6);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('Black House', 'black_house.jpg', 2);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('Half Girlfriend', 'half_girlfriend.jpg', 2);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('The Secret', 'the_secret.jpg', 4);
INSERT INTO book (book_name, image_url, publisher_id) VALUES ('The 3 Mistakes of My Life', 'the_3_mistakes_of_my_life.jpg', 2);

-- ======================
-- Book-Author Relationships
-- ======================
INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
INSERT INTO book_author (book_id, author_id) VALUES (2, 3);
INSERT INTO book_author (book_id, author_id) VALUES (3, 4);
INSERT INTO book_author (book_id, author_id) VALUES (3, 5);
INSERT INTO book_author (book_id, author_id) VALUES (4, 4);
INSERT INTO book_author (book_id, author_id) VALUES (5, 4);
INSERT INTO book_author (book_id, author_id) VALUES (5, 5);
INSERT INTO book_author (book_id, author_id) VALUES (6, 6);
INSERT INTO book_author (book_id, author_id) VALUES (7, 7);
INSERT INTO book_author (book_id, author_id) VALUES (8, 6);