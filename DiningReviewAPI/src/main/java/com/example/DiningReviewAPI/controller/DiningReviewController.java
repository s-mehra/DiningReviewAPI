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

    private void validateDiningReview(DiningReview review){

        if(ObjectUtils.isEmpty(review.getUserName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(review.getRestaurantId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(review.getPeanutScore()) && ObjectUtils.isEmpty(review.getEggScore()) && ObjectUtils.isEmpty(review.getDairyScore())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userRepository.findUserByName(review.getUserName());
        if( existingUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
