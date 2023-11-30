package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.model.*;
import com.example.DiningReviewAPI.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public DiningReviewController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, UserRepository userRepository){
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addDiningReview(@RequestBody DiningReview review){
        validateDiningReview(review);

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if(optionalRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        review.setReviewStatus(Status.PENDING);
        reviewRepository.save(review);
    }

    private void validateDiningReview(DiningReview review) {
        System.out.println("Validating dining review...");

        if (ObjectUtils.isEmpty(review.getUserName())) {
            System.out.println("Username is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty");
        }

        if (ObjectUtils.isEmpty(review.getRestaurantId())) {
            System.out.println("Restaurant ID is empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant ID cannot be empty");
        }

        if (ObjectUtils.isEmpty(review.getPeanutScore()) && ObjectUtils.isEmpty(review.getEggScore()) && ObjectUtils.isEmpty(review.getDairyScore())) {
            System.out.println("At least one of peanutScore, eggScore, or dairyScore must be provided.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one of peanutScore, eggScore, or dairyScore must be provided");
        }

        Optional<User> existingUser = userRepository.findUserByName(review.getUserName());
        if (existingUser.isEmpty()) {
            System.out.println("User not found by name: " + review.getUserName());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User not found");
        }
    }



}