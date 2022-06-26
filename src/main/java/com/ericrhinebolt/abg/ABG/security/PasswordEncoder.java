package com.ericrhinebolt.abg.ABG.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "password";
                String encodedPassword = encoder.encode(plainPassword);

                System.out.println(encodedPassword);
    }
}
