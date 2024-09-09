package com.kyron.automation.socialbook.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Book> list() {
        return booksRepository.findAll();
    }

    public Book save(Book book) {
        return booksRepository.save(book);
    }

    public Optional<Book> getBook(Long id) {
        Optional<Book> book = booksRepository.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }

        return book;
    }

    public void delete(Long id) {
        verifyExists(id);
        booksRepository.deleteById(id);
    }

    public void update(Book book) {
        verifyExists(book.getId());
        booksRepository.save(book);
    }

    private void verifyExists(Long id) {
        getBook(id);
    }

    public Review saveReview(Long bookId, Review review) {
        Book book = getBook(bookId).get();

        review.setBook(book);
        review.setDate(new Date());

        return reviewsRepository.save(review);
    }

    public List<Review> listReviews(Long bookId) {
        Book book = getBook(bookId).get();

        return book.getReviews();
    }
}
