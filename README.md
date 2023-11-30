# DiningReviewAPI Documentation
**Overview**

DiningReviewAPI is a RESTful API designed for managing user reviews of restaurants. It allows users to submit reviews, view pending reviews, and manage the acceptance or rejection of reviews.

**API Endpoints**

**1. Create User**

   Create a new user with allergies information.

```curl -X POST -H "Content-Type: application/json" -d '{"name": "John Doe", "city": "New York", "state": "NY", "zipcode": "10001", "peanutAllergies": false, "dairyAllergies": true, "eggAllergies": false}' http://localhost:3001/users```

**2. Create Restaurant**

   Create a new restaurant with its details and check restaurant id.

```curl -X POST -H "Content-Type: application/json" -d '{"name": "New Restaurant", "address": "123 Main St", "state": "NY", "city": "New York", "zipCode": "10001", "overallScore": "4.5", "peanutScore": "4", "eggScore": "3.5", "dairyScore": "4.2"}' http://localhost:3001/restaurants```

**3. Submit Review**

   Submit a review for a restaurant.

```curl -X POST -H "Content-Type: application/json" -d '{"userName": "John Doe", "restaurantId": 2, "peanutScore": 4, "eggScore": 5, "dairyScore": 3}' http://localhost:3001/reviews```

**4. View Pending Reviews**

   View a list of pending reviews.

```curl "http://localhost:3001/admin/reviews?status=PENDING"```

**5. Accept or Reject Reviews**

   Accept or reject a pending review by providing the review ID.


```curl -X PUT -H "Content-Type: application/json" -d '{"accept": true}' http://localhost:3001/admin/reviews/3```

```curl -X PUT -H "Content-Type: application/json" -d '{"accept": false}' http://localhost:3001/admin/reviews/5```

**6. View Accepted Reviews**

   View a list of accepted reviews.


```curl "http://localhost:3001/admin/reviews?status=ACCEPTED"```

**7. View Rejected Reviews**

   View a list of rejected reviews.

```curl "http://localhost:3001/admin/reviews?status=REJECTED"```
