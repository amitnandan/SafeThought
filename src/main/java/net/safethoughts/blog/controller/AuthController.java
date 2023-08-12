package net.safethoughts.blog.controller;

import net.safethoughts.blog.payload.LoginDto;
import net.safethoughts.blog.payload.RegisterDto;
import net.safethoughts.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }



//    @PostMapping(value = {"/login", "/signin"})

    //build login REST API - Sign IN rest api
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

//    @PostMapping(value = {"/login", "/signin"})
//    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
//        String token = authService.login(loginDto);
//
//        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
//        jwtAuthResponse.setAccessToken(token);
//
//        return ResponseEntity.ok(jwtAuthResponse);
//    }

    //register rest api

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
