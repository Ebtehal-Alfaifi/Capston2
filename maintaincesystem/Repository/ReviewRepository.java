package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Review findReviewByReviewId(Integer reviewId);
    List<Review> findAllByOrderId(Integer orderId);
}
