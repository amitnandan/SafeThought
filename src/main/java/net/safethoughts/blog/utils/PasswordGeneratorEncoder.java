package net.safethoughts.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordGeneratorEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordGeneratorEncoder.encode("admin"));
        System.out.println(passwordGeneratorEncoder.encode("amit"));

    }
}
