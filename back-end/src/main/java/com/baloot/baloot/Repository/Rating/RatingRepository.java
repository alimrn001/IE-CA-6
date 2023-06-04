package com.baloot.baloot.Repository.Rating;

import com.baloot.baloot.models.Rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating getRatingByUserUsernameAndCommodity_Id(String username, int commodityId);

    List<Rating> findRatingsByUserUsername(String username);

    List<Rating> findRatingsByCommodity_Id(int commodityId);

}
