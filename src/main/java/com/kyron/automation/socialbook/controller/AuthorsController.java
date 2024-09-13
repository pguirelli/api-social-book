package com.kyron.automation.socialbook.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kyron.automation.socialbook.model.Author;
import com.kyron.automation.socialbook.services.AuthorsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Author>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(authorsService.list());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Author author) {
        authorsService.save(author);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthor(@PathVariable("id") Long id) {
        Optional<Author> author = authorsService.getAuthor(id);

        return ResponseEntity.status(HttpStatus.OK).body(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorsService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Author author, @PathVariable("id") Long id) {
        author.setId(id);
        authorsService.update(author);

        return ResponseEntity.noContent().build();
    }

}
