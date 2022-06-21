package com.ericrhinebolt.abg.app;

import com.ericrhinebolt.abg.app.data.UserRepository;
import com.ericrhinebolt.abg.app.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testCreateUser(){
        User user = new User();

        user.setUserEmail("erhinebo@gmail.com");
        user.setUserName("erhinebo");
        user.setPassword("password");

        User savedUser = userRepository.save(user);
        assertEquals(savedUser, userRepository.getReferenceById(savedUser.getUserId()));
    }

    @Test
    public void testFindUserByEmail(){
        String userEmail = "erhinebo@gmail.com";

        User user = userRepository.findByUserEmail(userEmail);

        assertThat(user).isNotNull();
    }

//    @Test
//    public void testFindFavoritesByUserId(){
//        User user = userRepository.findByUserName("erhinebo");
//        Pageable pageable = null;
//        assertEquals(user.getFavoriteGames(), userRepository.findFavoritesByUserId(user, pageable));
//    }

}
