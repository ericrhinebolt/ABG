package com.ericrhinebolt.abg.app.service;

import com.ericrhinebolt.abg.app.data.GamesRepository;
import com.ericrhinebolt.abg.app.data.ReviewsRepository;
import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.models.Reviews;
import com.ericrhinebolt.abg.app.utils.pagination.Paged;
import com.ericrhinebolt.abg.app.utils.pagination.Paging;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final GamesRepository gamesRepository;

    public ReviewsService(ReviewsRepository reviewsRepository, GamesRepository gamesRepository) {
        this.reviewsRepository = reviewsRepository;
        this.gamesRepository = gamesRepository;
    }

    public Page<Reviews> searchReviews(int appId, Pageable pageable) {
        return reviewsRepository.findReviewByGame(appId, pageable);
    }

    public Page<Reviews> searchReviewsByTitle(String title, Pageable pageable) {
        List<Games> games = gamesRepository.findGamesByTitle(title);
        List<Reviews> review = new ArrayList<>();
        for (Games g : games) {
            int i = g.getAppId();
            List<Reviews> subReview = reviewsRepository.findReviewByAppId(i);
            review.addAll(subReview);
        }
        return new PageImpl<>(review);
    }

    public Paged<Reviews> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.DESC, "review_id");
        Page<Reviews> reviewsPage = reviewsRepository.findAllReviews(request);
        return new Paged<>(reviewsPage, Paging.of(reviewsPage.getTotalPages(), pageNumber, size));
    }

    //Changed to searchReviewsByTitle from Search reviews, changed int appId to String title

    public Paged<Reviews> getPage(int pageNumber, int size, String title) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.DESC, "review_id");
        Page<Reviews> reviewsPage = searchReviewsByTitle(title, request);
        return new Paged<>(reviewsPage, Paging.of(reviewsPage.getTotalPages(), pageNumber, size));
    }
}
