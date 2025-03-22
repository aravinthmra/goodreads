package com.example.goodreads.controller;

import com.example.goodreads.model.Publisher;
import com.example.goodreads.service.PublisherJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PublisherController {

    @Autowired
    private PublisherJpaService publisherJpaService;

    @GetMapping("/publishers")
    public ArrayList<Publisher> getPublishers(){
        return publisherJpaService.getPublishers();
    }

    @GetMapping("/publishers/{publisherId}")
    public Publisher getPublisherById(@PathVariable Integer publisherId) {
        return publisherJpaService.getPublisherById(publisherId);
    }
}
