package com.ericrhinebolt.abg.app.data;

import com.ericrhinebolt.abg.app.models.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {

    //Custom Query to find games by titleSearch. nativeQuery sets dialect to MariaDB
    @Query(value = "SELECT * FROM games WHERE LOWER(games.title) LIKE LOWER(CONCAT('%',:titleSearch,'%'))", nativeQuery = true)
    List<Games> findGamesByTitle(@Param("titleSearch") String titleSearch);

    @Query(value = "SELECT * FROM games WHERE LOWER(games.title) LIKE LOWER(CONCAT('%',:titleSearch,'%'))", nativeQuery = true)
    Page<Games> findGamesByTitlePage(@Param("titleSearch") String titleSearch, Pageable pageable);

    @Query(value = "SELECT * FROM games WHERE games.title != '' AND games.title IS NOT NULL", nativeQuery = true)
    Page<Games> findGameWithTitle(Pageable pageable);

    @Query(value = "SELECT * FROM games WHERE app_id = ?1", nativeQuery = true)
    Games findGameByAppId (int appId);


}