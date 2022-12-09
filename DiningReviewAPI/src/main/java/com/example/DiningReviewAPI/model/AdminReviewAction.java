package com.example.DiningReviewAPI.model;

import lombok.*;


public class AdminReviewAction {
    
    //Whether or not the review has been approved by an admin
    @Getter @Setter private Boolean accept;
}
