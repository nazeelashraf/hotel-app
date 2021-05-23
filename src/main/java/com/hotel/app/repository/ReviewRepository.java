package com.hotel.app.repository;

import com.hotel.app.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    List<Review> findReviewsByHotelHotelId(Long hotelId);

    @Query("from Review where hotel.hotelId=:hotelId AND traveler.travelerId=:travelerId")
    Optional<Review> fetchReviewByHotelAndTraveler(Long hotelId, Long travelerId);
}
