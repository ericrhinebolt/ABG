package com.ericrhinebolt.abg.ABG.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy="reviewUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id")
    )
    Set<Games> favoriteGames;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(password, user.password) && Objects.equals(reviews, user.reviews) && Objects.equals(favoriteGames, user.favoriteGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userEmail, password, reviews, favoriteGames);
    }
}
