package com.example.maintaincesystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    @NotNull(message = "Order ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer orderId;

    @NotNull(message = "Client ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer clientId;




    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(columnDefinition = "int not null")
    private Integer rating;

    @Size(max = 1000, message = "Comment must be less than 1000 characters")
    @Column(columnDefinition = "varchar(1000)") private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Review(Integer reviewId, Integer orderId, Integer clientId, Integer rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.clientId = clientId;

        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Review() {

    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public @NotNull(message = "Order ID cannot be null") Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull(message = "Order ID cannot be null") Integer orderId) {
        this.orderId = orderId;
    }

    public @NotNull(message = "Client ID cannot be null") Integer getClientId() {
        return clientId;
    }

    public void setClientId(@NotNull(message = "Client ID cannot be null") Integer clientId) {
        this.clientId = clientId;
    }


    public @NotNull(message = "Rating cannot be null") @Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") Integer getRating() {
        return rating;
    }

    public void setRating(@NotNull(message = "Rating cannot be null") @Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") Integer rating) {
        this.rating = rating;
    }

    public @Size(max = 1000, message = "Comment must be less than 1000 characters") String getComment() {
        return comment;
    }

    public void setComment(@Size(max = 1000, message = "Comment must be less than 1000 characters") String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
