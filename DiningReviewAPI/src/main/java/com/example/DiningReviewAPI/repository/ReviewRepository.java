package com.example.DiningReviewAPI.repository;

import com.example.DiningReviewAPI.model.DiningReview;
import com.example.DiningReviewAPI.model.Status;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<DiningReview, Long>{

    List<DiningReview> findDiningReviewsByRestaurantIdAndReviewStatus(long restaurantId, Status status);
    List<DiningReview> findDiningReviewsByReviewStatus(Status status);
    
}
