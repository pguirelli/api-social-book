package com.kyron.automation.socialbook.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kyron.automation.socialbook.model.Book;
import com.kyron.automation.socialbook.model.Review;
import com.kyron.automation.socialbook.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(booksService.list());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Book book) {
        booksService.save(book);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable("id") Long id) {
        Book book = booksService.getBook(id);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        booksService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Book book, @PathVariable("id") Long id) {
        book.setId(id);
        booksService.update(book);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> addReview(@PathVariable("id") Long id, @RequestBody Review review) {
        booksService.saveReview(id, review);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviews(@PathVariable("id") Long idBook) {
        List<Review> reviews = booksService.listReviews(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
}
