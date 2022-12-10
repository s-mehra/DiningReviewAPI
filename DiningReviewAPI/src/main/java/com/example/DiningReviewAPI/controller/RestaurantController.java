package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.model.*;
import com.example.DiningReviewAPI.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    
    private final RestaurantRepository restaurantRepository;
    private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant){
        validateNewRestaurant(restaurant);

        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    
    @GetMapping("/search")
    public Iterable<Restaurant> searchRestaurants(@RequestParam String zipCode, @RequestParam String allergen){
        validateZipCode(zipCode);

        Iterable<Restaurant> restaurants = Collections.emptyList();
        if (allergen.equalsIgnoreCase("peanuts")){
            restaurants = restaurantRepository.findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipCode);
        }else if (allergen.equalsIgnoreCase("egg")){
            restaurants = restaurantRepository.findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(zipCode);
        }else if (allergen.equalsIgnoreCase("dairy")){
            restaurants = restaurantRepository.findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipCode);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return restaurants;
    }
    
    private void validateNewRestaurant(Restaurant restaurant){
        if( ObjectUtils.isEmpty(restaurant.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        validateZipCode(restaurant.getZipCode());
        
        Optional<Restaurant> existingRestaurant = restaurantRepository.findRestaurantsByNameAndZipCode(restaurant.getName(),restaurant.getZipCode());
        if(existingRestaurant.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        
    }

    private void validateZipCode(String zipCode){
        if (!zipCodePattern.matcher(zipCode).matches()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }



}
