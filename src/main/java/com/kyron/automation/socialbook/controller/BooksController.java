package com.kyron.automation.socialbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyron.automation.socialbook.model.Book;
import com.kyron.automation.socialbook.repository.BooksRepository;

@RestController
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> list() {
        return booksRepository.findAll();
    }

}
