package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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

    @Test
    public void testFindByUserName(){
        User user = new User();

        user.setUserId(7);
        user.setUserEmail("erhinebo@gmail.com");
        user.setUserName("erhinebo");
        user.setPassword("password");

        assertThat(userRepository.findByUserName("erhinebo").equals(user));
    }

    @Test
    public void testFindFavoritesByUserId(){
        int user = 7;
        int appId = 1063730;
        assertThat(userRepository.findFavoritesByUserId(user).contains(appId));
    }

}
