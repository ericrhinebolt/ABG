package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.data.ReviewsRepository;
import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.models.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.function.BooleanSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class ReviewsRepositoryTest {

    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private GamesRepository gamesRepoistory;
    @Autowired
    private UserRepository userRepository;

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
        reviews.setReviewGame(gamesRepoistory.findGameByAppId(1248130));
        reviews.setReviewUser(userRepository.findByUserName("erhinebo"));
        reviewsRepository.save(reviews);
        Pageable pageable = null;
        Page<Reviews> review = reviewsRepository.findReviewByGame(1248130, pageable);
        List<Reviews> reviewList = review.toList();
        List<Reviews> reviewsListCheck = reviewsRepository.findReviewByAppId(1248130);
        assertThat(reviewList).isEqualTo(reviewsListCheck);
    }

    @Test
    void testFindReviewByAppId() {
        Reviews reviews = new Reviews();
        reviews.setReviewId(6);
        reviews.setReview("im reviewing");
        reviews.setRating(0);
        reviews.setReviewGame(gamesRepoistory.findGameByAppId(1248130));
        reviews.setReviewUser(userRepository.findByUserName("erhinebo"));
        reviewsRepository.save(reviews);
        Pageable pageable = null;
        Page<Reviews> review = reviewsRepository.findReviewByGame(1248130, pageable);
        List<Reviews> reviewList = review.toList();
        List<Reviews> reviewsListCheck = reviewsRepository.findReviewByAppId(1248130);
        assertThat(reviewList).isEqualTo(reviewsListCheck);
    }

    @Test
    void testFindReviewsById() {
        Reviews reviews = new Reviews();
        reviews.setReviewId(14);
        reviews.setReview("This game is fun.. If you love to farm and do repeated activities this game is for you. If you love PVE it has a lot of tasks that are related to pve only and if you love to do pvp in open world you can do it too with help of your clan or friends! I totally recommend this game for people who seek chill games and discover new things and craft too many things. You need to look for a lot of tips on the internet to help you understand some things in this game but it is super easy when you understand everything they provided for you. Also, I spent 800+ hours in this game and I can say I tried everything in it. But the game needs more updates and more content. Overall, I love this game and I will continue playing it on my free time.");
        reviews.setRating(0);
        reviews.setReviewUser(userRepository.findByUserName("boltz"));
        reviews.setReviewGame(gamesRepoistory.findGameByAppId(1063730));
        reviews.setDate("06/17/2022");

        Reviews testReview = reviewsRepository.findReviewsById(14);
        assertThat(testReview).isEqualTo(reviews);
    }
}