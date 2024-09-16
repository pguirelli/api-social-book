package com.kyron.automation.socialbook.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.kyron.automation.socialbook.model.Book;
import com.kyron.automation.socialbook.model.Review;
import com.kyron.automation.socialbook.repository.BooksRepository;
import com.kyron.automation.socialbook.repository.ReviewsRepository;
import com.kyron.automation.socialbook.services.exceptions.BookNotFoundException;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Cacheable(value = "books")
    public List<Book> list() {
        return booksRepository.findAll();
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book save(Book book) {
        return booksRepository.save(book);
    }

    @Cacheable(value = "books")
    public Optional<Book> getBook(Long id) {
        Optional<Book> book = booksRepository.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }

        return book;
    }

    @CacheEvict(value = "books", allEntries = true)
    public void delete(Long id) {
        verifyExists(id);
        booksRepository.deleteById(id);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void update(Book book) {
        verifyExists(book.getId());
        booksRepository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(value = "reviews", allEntries = true),
            @CacheEvict(value = "books", allEntries = true) })
    public Review saveReview(Long bookId, Review review) {
        Book book = getBook(bookId).get();

        review.setBook(book);
        review.setDate(new Date());

        return reviewsRepository.save(review);
    }

    @Cacheable(value = "reviews")
    public List<Review> listReviews(Long bookId) {
        Book book = getBook(bookId).get();

        return book.getReviews();
    }

    private void verifyExists(Long id) {
        getBook(id);
    }
}
