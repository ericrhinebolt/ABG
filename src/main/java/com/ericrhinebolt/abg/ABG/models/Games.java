package com.ericrhinebolt.abg.ABG.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="games")
public class Games {
//    Entity Model for Games table
    @Id
    @Column(name="app_id")
    private int appId;
    @Column
    private String title;

    @OneToMany(mappedBy = "reviewGame")
    private List<Reviews> reviewsList;

    public Games(int appId, String title) {
        this.appId = appId;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return appId == games.appId && Objects.equals(title, games.title) && Objects.equals(reviewsList, games.reviewsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, title, reviewsList);
    }
}
