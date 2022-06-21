package com.ericrhinebolt.abg.app.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="review_id")
    private int reviewId;
    @Column
    private String review;
    @Column
    private int rating;

    @Column
    private String date;
    @ManyToOne
    private Games reviewGame;
    @ManyToOne(fetch = FetchType.LAZY)
    private User reviewUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return reviewId == reviews.reviewId && rating == reviews.rating && Objects.equals(review, reviews.review) && Objects.equals(reviewGame, reviews.reviewGame) && Objects.equals(reviewUser, reviews.reviewUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, review, rating, reviewGame, reviewUser);
    }

}




//    @Column(name = "game_title")
//    private String gameTitle;

//    @Column(name = "app_id")
//    private int appId;
//    @Column(name = "user_name")
//    private String userName;
//    @Column(name = "user_id")
//    private int userId;