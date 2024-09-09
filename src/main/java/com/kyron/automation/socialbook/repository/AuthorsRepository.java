package com.kyron.automation.socialbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyron.automation.socialbook.model.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

}
