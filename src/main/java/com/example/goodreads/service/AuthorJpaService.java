package com.example.goodreads.service;

import com.example.goodreads.model.Author;
import com.example.goodreads.model.Book;
import com.example.goodreads.repository.AuthorJpaRepository;
import com.example.goodreads.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorJpaService implements AuthorRepository {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Autowired
    private BookJpaService bookJpaService;

    @Override
    public ArrayList<Author> getAuthors() {
        List<Author> authorList = authorJpaRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>(authorList);
        return authors;
    }

    @Override
    public Author getAuthorById(int authorId) {
        try {
            Author author = authorJpaRepository.findById(authorId).get();
            return author;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Author addAuthor(Author author) {
//        Author can exist even though a Book does not exist
        if(!(author.getBooks().isEmpty())) {
            List<Integer> bookIds = new ArrayList<>();
            for (Book book : author.getBooks()) {
                bookIds.add(book.getId());
            }

            List<Book> books = bookJpaService.getAllBooks(bookIds);
            author.setBooks(books);

            // save the author entity
            authorJpaRepository.save(author);

            // map author to the books
            for (Book book : books) {
                book.getAuthors().add(author);

                // update books in the database
                bookJpaService.updateBook(book.getId(), book);
            }


        } else authorJpaRepository.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(int authorId, Author author) {
        Author new_author = getAuthorById(authorId);
        if (author.getAuthorName() != null) new_author.setAuthorName(author.getAuthorName());
        authorJpaRepository.save(new_author);
        return new_author;
    }

    @Override
    public void deleteAuthor(int authorId) {
        if(getAuthorById(authorId) != null) {
            authorJpaRepository.deleteById(authorId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

    }

    @Override
    public List<Book> getAuthorBooks(int authorId) {
        Author author = getAuthorById(authorId);
        return author.getBooks();
    }

    // Retrieve multiple author entities from the database
    @Override
    public List<Author> getAllAuthors(List<Integer> authorIds) {
        return authorJpaRepository.findAllById(authorIds);
    }

}
