package com.ericrhinebolt.abg.app.data;

import com.ericrhinebolt.abg.app.models.Favorites;
import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE user_email = ?1", nativeQuery = true)
    User findByUserEmail (String userEmail);

    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findByUserName (String userName);

    @Query(value = "SELECT app_id FROM favorites WHERE user_id = ?1", nativeQuery = true)
    Set<Integer> findFavoritesByUserId(int userId);



//    get set of favorites
//    Page<Games> page = new Page<Games>;
//            if user id == favorites.userid{
//                page = page.add favorites.appid
//            return page
//    }
//    Page<Games> findFavoritesByUserId(int userId, Pageable pageable);
}