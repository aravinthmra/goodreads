package com.example.goodreads.service;

import com.example.goodreads.model.BookRowMapper;
import com.example.goodreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.goodreads.model.Book;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookH2Service implements BookRepository {

    @Autowired
    private JdbcTemplate db;


    @Override
    public ArrayList<Book> getBooks() {
        List<Book> bookList = db.query("SELECT * FROM book", new BookRowMapper());
        ArrayList<Book> books = new ArrayList<>(bookList);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        try {
            Book book = db.queryForObject("SELECT * FROM book WHERE id=?", new BookRowMapper(), bookId);
            return book;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Book addBook(Book book) {
        db.update("INSERT INTO book(name, imageUrl) VALUES (?, ?)", book.getName(), book.getImgUrl());

        Book savedBook = db.queryForObject("SELECT * FROM book WHERE name=? AND imageUrl=?", new BookRowMapper(), book.getName(), book.getImgUrl());
        return savedBook;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        if(book.getName() != null){
            db.update("UPDATE book SET name=? WHERE id=?", book.getName(), bookId);
        }
        if(book.getImgUrl() != null){
            db.update("UPDATE book SET imageUrl=? WHERE id=?", book.getImgUrl(), bookId);
        }
        return getBookById(bookId);
    }

    @Override
    public void deleteBook(int bookId) {
        db.update("DELETE FROM book WHERE id=?", bookId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}