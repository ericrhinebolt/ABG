package com.ericrhinebolt.abg.ABG.data;

import com.ericrhinebolt.abg.ABG.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Set;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE user_email = ?1", nativeQuery = true)
    User findByUserEmail (String userEmail);

    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findByUserName (String userName);

    @Query(value = "SELECT app_id FROM favorites WHERE user_id = ?1", nativeQuery = true)
    Set<Integer> findFavoritesByUserId(int userId);

}