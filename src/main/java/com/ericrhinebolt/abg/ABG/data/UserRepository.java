package com.ericrhinebolt.abg.ABG.data;

import com.ericrhinebolt.abg.ABG.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Set;


public interface UserRepository extends JpaRepository<User, Integer> {

//    Custom Query to select users by email
    @Query(value = "SELECT * FROM user WHERE user_email = ?1", nativeQuery = true)
    User findByUserEmail (String userEmail);

//    Custom Query to select users by username
    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findByUserName (String userName);

//    Custom Query to select a set of appIds from favorites table by userId
    @Query(value = "SELECT app_id FROM favorites WHERE user_id = ?1", nativeQuery = true)
    Set<Integer> findFavoritesByUserId(int userId);

    Boolean existsByUserEmail(String userEmail);

}