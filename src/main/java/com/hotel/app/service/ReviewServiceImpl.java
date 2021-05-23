package com.hotel.app.service;

import com.hotel.app.bean.SearchRequest;
import com.hotel.app.exception.CouldNotFindException;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Review;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.ReviewRepository;
import com.hotel.app.util.SpecificationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    SpecificationUtil specificationUtil;

    @Override
    public List<Review> getReviewsForHotel(Long hotelId) {
        return repository.findReviewsByHotelHotelId(hotelId);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return repository.findById(reviewId).orElseThrow(() -> new CouldNotFindException("reivew with id "+reviewId));
    }

    @Override
    public Review saveReview(Review review) throws Exception {
        Review review1 = repository.fetchReviewByHotelAndTraveler(review.getHotel().getHotelId(),
                review.getTraveler().getTravelerId()).orElseThrow(() -> new CouldNotFindException("hotel/traveler"));
        review.setReviewId(review1.getReviewId());
        review = repository.save(review);
        Hotel hotel = review.getHotel();
        hotel.setRating(
                review1.getHotel().getReviews().stream()
                        .map(Review::getRatingValue)
                        .mapToDouble(Double::valueOf)
                        .summaryStatistics().getAverage());

        hotelRepository.save(hotel);

        return review;
    }

    @Override
    public List<Review> getAllReviewsByCriteria(SearchRequest request) {
        Specification specification = specificationUtil.andAll(request.getCriteria());
        Sort sort = Sort.by("ratingValue").descending();
        if(request.getSortBy()!=null)
            sort = Sort.by(request.getSortBy());
        if(!request.getAscending()) sort = sort.descending();

        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageItems(), sort);

        if(specification==null) return repository.findAll();
        return repository.findAll(specification, pageRequest).toList();
    }

    @Override
    public void deleteReview(Long reviewId) {
        repository.delete(getReviewById(reviewId));
    }
}
