package com.example.DiningReviewAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

@Entity
public class User{

    //unique id for each User object
    @Id
    @GeneratedValue
    private Long id;

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
    @Getter @Setter private Integer zipcode;

    /**
     * Whether the user is interested in peanut allergies or not
     * @param peanutAllergies
     *          new value for the user's interest in peanut allergies
     * @return
     *          current value for the user's interest in peanut allergies
     */
    @Getter @Setter private boolean peanutAllergies;

    /**
     * Whether the user is interested in egg allergies or not
     * @param eggAllergies
     *          new value for the user's interest in egg allergies
     * @return
     *          current value for the user's interest in egg allergies
     */
    @Getter @Setter private boolean eggAllergies;

    /**
     * Whether the user is interested in dairy allergies or not
     * @param dairyAllergies
     *          new value for the user's interest in dairy allergies
     * @return
     *          current value for the user's interest in dairy allergies
     */
    @Getter @Setter private boolean dairyAllergies;


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
    public User(String name, String city, String state, Integer zipcode, boolean peanutAllergies, boolean eggAllergies, boolean dairyAllergies){
        this.name = name;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.peanutAllergies = peanutAllergies;
        this.eggAllergies = eggAllergies;
        this.dairyAllergies = dairyAllergies;
    }

    
}