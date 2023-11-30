package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.model.*;
import com.example.DiningReviewAPI.repository.*;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;


    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public AdminController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository){
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;

    }

    @GetMapping("/reviews")
    public List<DiningReview> getReviewsByStatus(@RequestParam String status){
        Status reviewStatus = Status.PENDING;

        try{
            reviewStatus = Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return reviewRepository.findDiningReviewsByReviewStatus(reviewStatus);
    }

    @PutMapping("/reviews/{reviewId}")
    public void performReviewAction (@PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction){

        Optional<DiningReview> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DiningReview review = optionalReview.get();

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if(optionalRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if(adminReviewAction.getAccept()){
            review.setReviewStatus(Status.ACCEPTED);
        } else {
            review.setReviewStatus(Status.REJECTED);
        }

        reviewRepository.save(review);
        updateRestaurantReviewScores(optionalRestaurant.get());
    }

    private void updateRestaurantReviewScores(Restaurant restaurant){
        List<DiningReview> reviews = reviewRepository.findDiningReviewsByRestaurantIdAndReviewStatus(restaurant.getId(), Status.ACCEPTED);

        if (reviews.size() == 0){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int peanutSum = 0;
        int peanutCount = 0;
        int eggSum = 0;
        int eggCount = 0;
        int dairySum = 0;
        int dairyCount = 0;

        for (DiningReview review : reviews){
            if(!ObjectUtils.isEmpty(review.getPeanutScore())){
                peanutSum += review.getPeanutScore();
                peanutCount++;
            }

            if(!ObjectUtils.isEmpty(review.getEggScore())){
                eggSum += review.getEggScore();
                eggCount++;
            }

            if(!ObjectUtils.isEmpty(review.getDairyScore())){
                dairySum += review.getDairyScore();
                dairyCount++;
            }
        }

        int totalCount = peanutCount + eggCount + dairyCount;
        int totalSum = peanutSum + eggSum + dairySum;

        float overallScore = (float) totalSum / totalCount;
        restaurant.setOverallScore(decimalFormat.format(overallScore));

        if (peanutCount > 0){
            float peanutScore = (float) peanutSum / peanutCount;
            restaurant.setPeanutScore(decimalFormat.format(peanutScore));
        }

        if (eggCount > 0){
            float eggScore = (float) eggSum / eggCount;
            restaurant.setEggScore(decimalFormat.format(eggScore));
        }

        if (dairyCount > 0){
            float dairyScore = (float) dairySum / dairyCount;
            restaurant.setDairyScore(decimalFormat.format(dairyScore));
        }


        restaurantRepository.save(restaurant);
    }



}
