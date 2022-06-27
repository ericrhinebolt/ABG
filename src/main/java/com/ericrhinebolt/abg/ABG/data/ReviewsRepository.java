package com.ericrhinebolt.abg.ABG.data;

import com.ericrhinebolt.abg.ABG.models.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

//    Custom Query to select all reviews as a pageable collection
    @Query(value = "SELECT * FROM reviews WHERE reviews.review != ''", nativeQuery = true)
    Page<Reviews> findAllReviews(Pageable pageable);

//    Custom Query to select reviews as a pageable collection based on appId
    @Query(value = "SELECT * FROM reviews WHERE review_game_app_id LIKE :appId", nativeQuery = true)
    Page<Reviews> findReviewByGame(@Param("appId") int appId, Pageable pageable);

//    Custom Query to select reviews as a list collection based on appId
    @Query(value = "SELECT * FROM reviews WHERE review_game_app_id LIKE ?1", nativeQuery = true)
    List<Reviews> findReviewByAppId(int appId);

//    Custom Query to select reviews based on review ID
    @Query(value= "SELECT * FROM reviews WHERE review_id = ?1", nativeQuery = true)
    Reviews findReviewsById(int reviewId);
}