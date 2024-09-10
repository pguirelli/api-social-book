package com.kyron.automation.socialbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyron.automation.socialbook.model.Author;
import com.kyron.automation.socialbook.repository.AuthorsRepository;
import com.kyron.automation.socialbook.services.exceptions.AuthorAlreadyExistsException;
import com.kyron.automation.socialbook.services.exceptions.AuthorNotFoundException;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    public List<Author> list() {
        return authorsRepository.findAll();
    }

    public Author save(Author author) {
        if (author.getId() != null) {
            Author a = authorsRepository.findById(author.getId()).get();

            if (a != null) {
                throw new AuthorAlreadyExistsException();
            }
        }

        return authorsRepository.save(author);
    }

    public Optional<Author> getAuthor(Long id) {
        Optional<Author> author = authorsRepository.findById(id);

        if (author.isEmpty()) {
            throw new AuthorNotFoundException();
        }

        return author;
    }

    public void delete(Long id) {
        verifyExists(id);
        authorsRepository.deleteById(id);
    }

    public void update(Author author) {
        verifyExists(author.getId());
        authorsRepository.save(author);
    }

    private void verifyExists(Long id) {
        getAuthor(id);
    }
}
