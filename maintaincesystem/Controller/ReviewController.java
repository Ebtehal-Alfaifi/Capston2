package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.Model.Review;
import com.example.maintaincesystem.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
//@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity getReviewsByOrderId(@PathVariable Integer orderId) {
        List<Review> reviews = reviewService.getReviewsByOrderId(orderId);
        return ResponseEntity.status(200).body(reviews);
    }


//add rev
@PostMapping("/add")
public ResponseEntity addrev(@RequestBody @Valid Review review) {

    reviewService.createReview(review);
    return ResponseEntity.status(200).body(new Api("Review submitted successfully"));
    }


    public ResponseEntity delete(@PathVariable Integer reviewId){
        reviewService.Delete(reviewId);
        return ResponseEntity.status(200).body(new Api("delete success"));
    }

    //rating
 @PostMapping("/rate/{orderId}/{clientId}/{rating}")
 public ResponseEntity submitRating(@PathVariable Integer orderId
         , @PathVariable Integer clientId,
                                    @PathVariable Integer rating) {
        Review review = reviewService.submitRating(orderId, clientId, rating);
        return ResponseEntity.status(200).body(new Api("Rating submitted successfully"));
    }

//add coment
    @PostMapping("/comment/{orderId}/{clientId}")
    public ResponseEntity submitComment(@PathVariable Integer orderId,
                                        @PathVariable Integer clientId,
                                        @RequestParam String comment) {
        reviewService.submitComment(orderId, clientId, comment);
        return ResponseEntity.status(200).body(new Api("Comment submitted successfully"));
    }


}