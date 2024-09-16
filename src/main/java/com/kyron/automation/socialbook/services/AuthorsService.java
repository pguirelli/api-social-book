package com.kyron.automation.socialbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.kyron.automation.socialbook.model.Author;
import com.kyron.automation.socialbook.repository.AuthorsRepository;
import com.kyron.automation.socialbook.services.exceptions.AuthorAlreadyExistsException;
import com.kyron.automation.socialbook.services.exceptions.AuthorNotFoundException;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Cacheable(value = "authors")
    public List<Author> list() {
        return authorsRepository.findAll();
    }

    @CacheEvict(value = "authors", allEntries = true)
    public Author save(Author author) {
        if (author.getId() != null) {
            Optional<Author> a = authorsRepository.findById(author.getId());

            if (a.isEmpty()) {
                throw new AuthorAlreadyExistsException();
            }
        }

        return authorsRepository.save(author);
    }

    @Cacheable(value = "authors")
    public Optional<Author> getAuthor(Long id) {
        Optional<Author> author = authorsRepository.findById(id);

        if (author.isEmpty()) {
            throw new AuthorNotFoundException();
        }

        return author;
    }

    @CacheEvict(value = "authors", allEntries = true)
    public void delete(Long id) {
        verifyExists(id);
        authorsRepository.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "authors", allEntries = true),
            @CacheEvict(value = "books", allEntries = true) })
    public void update(Author author) {
        verifyExists(author.getId());
        authorsRepository.save(author);
    }

    private void verifyExists(Long id) {
        getAuthor(id);
    }
}
