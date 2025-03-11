package com.example.goodreads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.example.goodreads.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookService implements BookRepository {
    // Using hashMap to store the books
    private HashMap<Integer, Book> hmap = new HashMap<Integer, Book>();
    int uniqueBookId = 3;

    public BookService() {
        Book b1 = new Book(1, "Harry potter", "harry_poter.jpg");
        Book b2 = new Book(2, "Rise", "rise.jpg");
        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }

    @Override
    public ArrayList<Book> getBooks() {
        Collection<Book> bookCollection = hmap.values();
        ArrayList<Book> books = new ArrayList<>(bookCollection);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = hmap.get(bookId);
        if(book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return book;
    }

    @Override
    public Book addBook(Book book) {
        book.setId(uniqueBookId);
        hmap.put(uniqueBookId, book);
        uniqueBookId += 1;

        return book;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book existingBook = getBookById(bookId);

        if(existingBook == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if(book.getName() != null) existingBook.setName(book.getName());

        if(book.getImgUrl() != null) existingBook.setImgUrl(book.getImgUrl());

        hmap.put(bookId, existingBook);
        return existingBook;
    }

    @Override
    public void deleteBook(int bookId){
        Book existingBook = getBookById(bookId);

        if(existingBook == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else {
            hmap.remove(bookId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}
