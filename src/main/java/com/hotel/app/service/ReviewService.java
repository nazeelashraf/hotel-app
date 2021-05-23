package com.hotel.app.service;

import com.hotel.app.bean.SearchRequest;
import com.hotel.app.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsForHotel(Long hotelId);
    Review getReviewById(Long reviewId);
    Review saveReview(Review review) throws Exception;
    List<Review> getAllReviewsByCriteria(SearchRequest request);
    void deleteReview(Long reviewId);
}
