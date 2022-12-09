package com.example.DiningReviewAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

/**
 * Class represents a restaurant entity in the model
 * Each object will have ratings, name and location by which they can be searched for
 * 
 * @author Soham Mehra @version 12.08.2022
 */
@Entity
public class Restaurant {

    //unique id for each restaurant object
    @Id
    @GeneratedValue
    private Long id;

    //instance variables to represent class attribute
    //Utilizing Lombok entities for ease getting and setting attributes

    /**
     * Name of the restaurant
     * @param name
     *          New name of the restaurant
     * @return
     *          Current name of the restaurant
     */
    @Getter @Setter private String name;

    /**
     * Address of the restaurant
     * @param address
     *          New address of the restaurant
     * @return
     *          Current address of the restaurant
     */
    @Getter @Setter private String address;

    /**
     * State that the restaurant is located in
     * @param state
     *          New state the restaurant is located in
     * @return
     *          Current state the restaurant is located in
     */
    @Getter @Setter private String state;

    /**
     * City that the restaurant is located in
     * @param city
     *          New city that the restaurant is located in
     * @return
     *          Current city that the restaurant is located in
     */
    @Getter @Setter private String city;

    /**
     * Zipcode of the restaurants location
     * @param zipCode
     *          New zipcode of the restaurant's location
     * @return
     *          Current zipcode of the restaurant's location
     */
    @Getter @Setter private Long zipCode;

    /**
     * Overall rating for the restaurant
     * @param overallScore
     *          New overall score for the restaurant
     * @return
     *          Current overall score for the restaurant
     */
    @Getter @Setter private String overallScore;

    /**
     * Peanut score for the restaurant
     * @param peanutScore
     *          New peanut score for the restaurant
     * @return
     *          Current peanut score for the restaurant
     */
    @Getter @Setter private String peanutScore;

    /**
     * Egg score for the restaurant
     * @param eggScore
     *          New egg score for the restaurant
     * @return
     *          Current egg score for the restaurant
     */
    @Getter @Setter private String eggScore;

    /**
     * Dairy score for the restaurant
     * @param dairyScore
     *          New dairy score for the restaurant
     * @return
     *          Current dairy score for the restaurant
     */
    @Getter @Setter private String dairyScore;

    /**
     * Constructor for restaurant entity
     * @param name
     *          Name of restaurant
     * @param address
     *          Address of restaurant
     * @param state
     *          State the restaurant is located in
     * @param city
     *          City the restaurant is located in
     * @param zipCode
     *          Zipcode of the restaurant
     * @param overallScore
     *          Overall score of the restaurant
     * @param peanutScore
     *          Peanut score of the restaurant
     * @param eggScore
     *          Egg score of the restaurant
     * @param dairyScore
     *          Dairy score of the restaurant
     */
    public Restaurant(String name, String address, String state, String city, Long zipCode, String overallScore, String peanutScore, String eggScore, String dairyScore){
        this.name = name;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.overallScore = overallScore;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;

    }

    
    
}
