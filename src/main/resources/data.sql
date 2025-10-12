insert into publisher (publisher_name) VALUES ('Bloomsbury Publishing');
insert into publisher (publisher_name) VALUES ('Rupa Publications');
insert into publisher (publisher_name) VALUES ('Knopf Canada');
insert into publisher (publisher_name) VALUES ('Simon & Schuster');
insert into publisher (publisher_name) VALUES ('Penguin Random House');
insert into publisher (publisher_name) VALUES ('Viking Press');

insert into author(author_name) values('J.K.Rowling');
insert into author(author_name) values('Larry Niven');
insert into author(author_name) values('Jerry Pournelle');
insert into author(author_name) values('Stephen King');
insert into author(author_name) values('Peter Straub');
insert into author(author_name) values('Chetan Bhagat');
insert into author(author_name) values('Rhonda Byrne');

insert into book (book_name, image_url, publisher_id) VALUES ('Harry Potter and the Sorcerer''s Stone', 'harry_potter_and_the_sorcerers_stone.jpg', 1);
insert into book (book_name, image_url, publisher_id) VALUES ('The Mote in Gods Eye', 'mote_in_gods_eye.jpg', 3);
insert into book (book_name, image_url, publisher_id) VALUES ('The Talisman', 'the_talisman.jpg', 1);
insert into book (book_name, image_url, publisher_id) VALUES ('The Stand', 'the_stand.jpg', 6);
insert into book (book_name, image_url, publisher_id) VALUES ('Black House', 'black_house.jpg', 2);
insert into book (book_name, image_url, publisher_id) VALUES ('Half Girlfriend', 'half_girlfriend.jpg', 2);
insert into book (book_name, image_url, publisher_id) VALUES ('The Secret', 'the_secret.jpg', 4);
insert into book (book_name, image_url, publisher_id) VALUES ('The 3 Mistakes of My Life', 'the_3_mistakes_of_my_life.jpg', 2);

INSERT INTO book_author(book_id, author_id) VALUES (1, 1);
INSERT INTO book_author(book_id, author_id) VALUES (2, 2);
INSERT INTO book_author(book_id, author_id) VALUES (2, 3);
INSERT INTO book_author(book_id, author_id) VALUES (3, 4);
INSERT INTO book_author(book_id, author_id) VALUES (3, 5);
INSERT INTO book_author(book_id, author_id) VALUES (4, 4);
INSERT INTO book_author(book_id, author_id) VALUES (5, 4);
INSERT INTO book_author(book_id, author_id) VALUES (5, 5);
INSERT INTO book_author(book_id, author_id) VALUES (6, 6);
INSERT INTO book_author(book_id, author_id) VALUES (7, 7);
INSERT INTO book_author(book_id, author_id) VALUES (8, 6);