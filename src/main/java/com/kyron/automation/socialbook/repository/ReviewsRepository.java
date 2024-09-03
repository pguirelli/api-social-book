package com.kyron.automation.socialbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyron.automation.socialbook.model.Review;

public interface ReviewsRepository extends JpaRepository<Review, Long> {

}
