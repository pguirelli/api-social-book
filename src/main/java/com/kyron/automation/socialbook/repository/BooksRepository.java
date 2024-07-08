package com.kyron.automation.socialbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyron.automation.socialbook.model.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {

}
