package com.example.DiningReviewAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

/**
 * class represents a dining review entity in the api model
 * each instance will represent a review submitted by a user about a restaurant
 * 
 * @author Soham Mehra @version 12.08.2022
 */
@Entity
public class DiningReview{
    
    //unique id for each Dining Review object
    @Id
    @GeneratedValue
    private Long id;

    //instance variables to represent class attribute
    //Utilizing Lombok entities for ease getting and setting attributes

    /**
     * User Name of the person who is leaving the review
     * @param userName
     *          new username of person leaving review
     * @return
     *          current username of person who is leaving review
     */
    @Getter @Setter private String userName;

    /**
     * restaurant the review is being left for 
     * @param restaurant
     *          the new restaurant that the user would like their review to be for
     * @return
     *          the current restaurant that the user is writing a review for
     */
    @Getter @Setter private Long restaurantId;

    /**
     * optional peanutScore for the restaurant
     * @param peanutScore
     *          the new peanut score for the restaurant
     * @return
     *          the current peanut score for the restaurant review
     */
    @Getter @Setter private Integer peanutScore;

    /**
     * optional eggScore for the restaurant
     * @param eggScore
     *          the new egg score for the restaurant
     * @return
     *          the current egg score for the restaurant review
     */
    @Getter @Setter private Integer eggScore;

    /**
     * optional dairy score for the restaurant
     * @param peanutScore
     *          the new dairy score for the restaurant
     * @return
     *          the current dairy score for the restaurant review
     */
    @Getter @Setter private Integer dairyScore;

    /**
     * optional commentary for the restaurant for this review
     * @param peanutScore
     *          the new for the restaurant for this review
     * @return
     *          the current commentary for the restaurant review
     */
    @Getter @Setter private String commentary;

    /**
     * status of the review: whether it is pending, rejected, or accepted
     * @param reviewStatus
     *          the new review status for the review
     * @return
     *          the current review status for the review            
     */
    @Getter @Setter private Status reviewStatus;

    /**
     * Constructor for Dining Review Entity
     * @param userName
     *          user who is leaving the review
     * @param restaurant
     *          restaurant for which the review is being left
     * @param peanutScore
     *          score for peanut allergen handling
     * @param eggScore
     *          score for egg allergen handling
     * @param dairyScore
     *          score for dairy allergen handling
     * @param commentary
     *          additional commentary that the user has on the restaurant
     */
    public DiningReview(String userName, Long restaurantId, Integer peanutScore, Integer eggScore, Integer dairyScore, String commentary){
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.peanutScore = eggScore;
        this.dairyScore = dairyScore;
        this.commentary = commentary;


    }
}