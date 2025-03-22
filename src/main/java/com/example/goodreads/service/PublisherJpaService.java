package com.example.goodreads.service;

import com.example.goodreads.model.Publisher;
import com.example.goodreads.repository.PublisherJpaRepository;
import com.example.goodreads.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherJpaService implements PublisherRepository {

    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Override
    public ArrayList<Publisher> getPublishers() {
        List<Publisher> publisherList = publisherJpaRepository.findAll();
        ArrayList<Publisher> publishers = new ArrayList<>(publisherList);
        return publishers;
    }

    @Override
    public Publisher getPublisherById(int publisherId) {
        try {
            Publisher publisher = publisherJpaRepository.findById(publisherId).get();
            return publisher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        publisherJpaRepository.save(publisher);
        return publisher;
    }

    @Override
    public Publisher updatePublisher(int publisherId, Publisher publisher) {
        Publisher newPublisher = getPublisherById(publisherId);

        if(publisher.getPublisherName() != null) newPublisher.setPublisherName(publisher.getPublisherName());

        publisherJpaRepository.save(newPublisher);
        return newPublisher;
    }

    @Override
    public void deletePublisher(int publisherId) {
        if (getPublisherById(publisherId) != null)
            publisherJpaRepository.deleteById(publisherId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
