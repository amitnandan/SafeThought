package net.safethoughts.blog.service;


import net.safethoughts.blog.payload.LoginDto;
import net.safethoughts.blog.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);


    String register(RegisterDto registerDto);
}
