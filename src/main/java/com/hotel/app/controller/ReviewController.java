package com.hotel.app.controller;

import com.hotel.app.bean.SearchRequest;
import com.hotel.app.model.Review;
import com.hotel.app.model.Room;
import com.hotel.app.repository.ReviewRepository;
import com.hotel.app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    ReviewService service;

    @GetMapping("{hotelId}")
    List<Review> getReviewsByHotel(@PathVariable Long hotelId) {
        return service.getReviewsForHotel(hotelId);
    }

    @PostMapping
    Review saveReview(@RequestBody Review review) throws Exception {
        return service.saveReview(review);
    }

    @PostMapping("search")
    public List<Review> getAllHotelsByCriteria(@RequestBody SearchRequest request) {
        return service.getAllReviewsByCriteria(request);
    }

    @DeleteMapping("{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        service.deleteReview(reviewId);
    }

}
