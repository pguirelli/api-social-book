package com.kyron.automation.socialbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyron.automation.socialbook.model.Book;
import com.kyron.automation.socialbook.repository.BooksRepository;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list() {
        return booksRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Book book) {
        booksRepository.save(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Book> getBook(@PathVariable("id") Long id) {
        return booksRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        booksRepository.deleteById(id);
    }
}
