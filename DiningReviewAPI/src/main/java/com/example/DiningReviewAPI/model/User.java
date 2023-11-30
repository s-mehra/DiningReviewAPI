package com.example.DiningReviewAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

/**
 * Class represents a user entity who would submit reviews about a restaurant in the API model
 *
 * @author Soham Mehra @version 12.7.2022
 *
 */
@Entity
@Table(name = "`User`")
public class User{

    //unique id for each User object
    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    //instance variables to represent class attribute
    //Utilizing Lombok entities for ease getting and setting attributes

    /**
     * Name of the user
     * @param name
     *          new value for name of the user
     * @return
     *          current name of the user
     */
    @Getter @Setter private String name;

    /**
     * City the user is present in
     * @param name
     *          new value for the city the user resides in
     * @return
     *          current city the user resides in
     */
    @Getter @Setter private String city;

    /**
     * State the user resides in
     * @param state
     *          new value for state the user resides in
     * @return
     *          current state the user resides in
     */
    @Getter @Setter private String state;

    /**
     * zipcode of the user
     * @param zipcode
     *          new value for the user's zipcode
     * @return
     *          user's current zipcode
     */
    @Getter @Setter private String zipcode;

    /**
     * Whether the user is interested in peanut allergies or not
     * @param peanutAllergies
     *          new value for the user's interest in peanut allergies
     * @return
     *          current value for the user's interest in peanut allergies
     */
    @Getter @Setter private Boolean peanutAllergies;

    /**
     * Whether the user is interested in egg allergies or not
     * @param eggAllergies
     *          new value for the user's interest in egg allergies
     * @return
     *          current value for the user's interest in egg allergies
     */
    @Getter @Setter private Boolean eggAllergies;

    /**
     * Whether the user is interested in dairy allergies or not
     * @param dairyAllergies
     *          new value for the user's interest in dairy allergies
     * @return
     *          current value for the user's interest in dairy allergies
     */
    @Getter @Setter private Boolean dairyAllergies;

    public User() {
    }

    /**
     * This method is the constructor for the User class
     * @param name
     *          name of the user
     * @param city
     *          city the user stays in
     * @param state
     *          state the user stays in
     * @param zipcode
     *          user's zipcode
     * @param peanutAllergies
     *          true if the user has peanut allergies as an interest
     * @param eggAllergies
     *          true if the user has egg allergies as an interest
     * @param dairyAllergies
     *          true if the user has dairy allergies as an interest
     */
    public User(String name, String city, String state, String zipcode, Boolean peanutAllergies, Boolean eggAllergies, Boolean dairyAllergies){
        this.name = name;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.peanutAllergies = peanutAllergies;
        this.eggAllergies = eggAllergies;
        this.dairyAllergies = dairyAllergies;
    }


}