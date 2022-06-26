package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.ReviewsRepository;
import com.ericrhinebolt.abg.ABG.models.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class ReviewsRepositoryTest {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Test
    void testFindAllReviews() {
        Pageable pageable = null;
        Page<Reviews> reviews = reviewsRepository.findAllReviews(pageable);
        List<Reviews> reviewsList = reviews.getContent();
        List<Reviews> otherList = reviewsRepository.findAll();
        assertEquals(reviewsList, otherList);
    }

    @Test
    void testFindReviewByGame() {
        Reviews reviews = new Reviews();
        reviews.setReviewId(6);
        reviews.setReview("im reviewing");
        reviews.setRating(0);

        Pageable pageable = null;
        Page<Reviews> review = reviewsRepository.findReviewByGame(1248130, pageable);
        List<Reviews> reviewsList = review.getContent();
        assertThat(reviewsList.contains(reviews));
    }

    @Test
    void testFindReviewByAppId() {
        Reviews reviews = new Reviews();
        reviews.setReviewId(6);
        reviews.setReview("im reviewing");
        reviews.setRating(0);

        List<Reviews> reviewsList = reviewsRepository.findReviewByAppId(1248130);
        assertThat(reviewsList.contains(reviews));
    }

    @Test
    void testFindReviewsById() {
        Reviews reviews = new Reviews();
        reviews.setReviewId(6);
        reviews.setReview("im reviewing");
        reviews.setRating(0);

        Reviews testReview = reviewsRepository.findReviewsById(6);
        assertThat(testReview.equals(reviews));
    }
}