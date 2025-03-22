package com.example.goodreads.repository;

import com.example.goodreads.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PublisherRepository {
    ArrayList<Publisher> getPublishers();
    Publisher getPublisherById(int publisherId);
}
