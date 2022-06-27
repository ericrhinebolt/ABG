package com.ericrhinebolt.abg.ABG.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="reviews")
public class Reviews {
//    Entity Model of Reviews table
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
        return reviewId == reviews.reviewId && rating == reviews.rating && Objects.equals(review, reviews.review) && Objects.equals(date, reviews.date) && Objects.equals(reviewGame, reviews.reviewGame) && Objects.equals(reviewUser, reviews.reviewUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, review, rating, date, reviewGame, reviewUser);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", date='" + date + '\'' +
                ", reviewGame=" + reviewGame +
                ", reviewUser=" + reviewUser +
                '}';
    }
}
