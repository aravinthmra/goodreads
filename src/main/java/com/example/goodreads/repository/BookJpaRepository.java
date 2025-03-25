package com.example.goodreads.repository;

import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Integer> {

    ArrayList<Book> findByPublisher(Publisher publisher);
}
