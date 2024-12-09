package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Order;
import com.example.maintaincesystem.Model.Review;
import com.example.maintaincesystem.Repository.OrderRepository;
import com.example.maintaincesystem.Repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@AllArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private OrderRepository orderRepository;

    public ReviewService(ReviewRepository reviewRepository, OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
    }

    //get  all review by order id
    public List<Review> getReviewsByOrderId(Integer orderId) {
        return reviewRepository.findAllByOrderId(orderId);
    }


    //*************End point*******************
    //creat new
    public Review createReview(Review review) {
        Order order = orderRepository.findOrderByOrderId(review.getOrderId());
        if (order == null) {
            throw new ApiException("Order not found with ID: ");
        }
        if (!"Completed".equals(order.getStatus())) {
            throw new ApiException("Reviews can only be submitted for completed orders.");
        }
        if (!order.getClientId().equals(review.getClientId())) {
            throw new ApiException("Client ID mismatch.");
        }
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }


    public void Delete(Integer reviewId){
        Review review=reviewRepository.findReviewByReviewId(reviewId);
        if (review==null){
            throw new ApiException("review not found");
        }
        reviewRepository.delete(review);
    }


    //******************EndPoint****************
    //submet rating
    public Review submitRating(Integer orderId, Integer clientId, Integer rating) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order == null) {
            throw new ApiException("Order not found with ID: " + orderId);
        }
        if (!"Completed".equals(order.getStatus())) {
            throw new ApiException("Ratings can only be submitted for completed orders.");
        }
        if (!order.getClientId().equals(clientId)) {
            throw new ApiException("Client ID mismatch.");
        }
        Review review = new Review();
        review.setOrderId(orderId);
        review.setClientId(clientId);
        review.setRating(rating);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review submitComment(Integer orderId, Integer clientId, String comment) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order == null) {
            throw new ApiException("Order not found with ID: ");
        }
        if (!"Completed".equals(order.getStatus())) {
            throw new ApiException("Comments can only be submitted for completed orders.");
        }
        if (!order.getClientId().equals(clientId)) {
            throw new ApiException("Client ID mismatch.");
        }
        Review review = new Review();
        review.setOrderId(orderId);
        review.setClientId(clientId);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);

    }
}
