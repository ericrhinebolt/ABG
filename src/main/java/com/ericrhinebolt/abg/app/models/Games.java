package com.ericrhinebolt.abg.app.models;

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
    @Id
    @Column(name="app_id")
    private int appId;
    @Column
    private String title;

    @OneToMany(mappedBy = "reviewGame")
    private List<Reviews> reviewsList;

//    @ManyToMany(mappedBy = "favoriteGames")
//    List<User> favorites;

//    @Column
//    private String genre;
//    @Column
//    private String description;

    public Games(int appId, String title) {
        this.appId = appId;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return appId == games.appId && Objects.equals(title, games.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, title);
    }
}
