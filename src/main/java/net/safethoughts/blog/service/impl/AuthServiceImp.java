package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Role;
import net.safethoughts.blog.entity.User;
import net.safethoughts.blog.exceptions.BlogAPIException;
import net.safethoughts.blog.payload.LoginDto;
import net.safethoughts.blog.payload.RegisterDto;
import net.safethoughts.blog.repository.RoleRepository;
import net.safethoughts.blog.repository.UserRepository;
import net.safethoughts.blog.security.JwtTokenProvider;
import net.safethoughts.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private RoleRepository roleRepository ;

    private PasswordEncoder passwordEncoder ;

    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImp(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               loginDto.getUsernameOrEmail(),
               loginDto.getPassword()
        ));



        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        //return "USER LOGGED IN SUCCESSFULLY !";
        return token;

        //return null;

    }

    @Override
    public String register(RegisterDto registerDto) {

    //username already exists or not(Check for username in db)

    if(userRepository.existsByUsername(registerDto.getUsername()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST ,"USERNAME already exists");

    //check for email exists or not
    if(userRepository.existsByEmail(registerDto.getEmail()))
        throw new BlogAPIException(HttpStatus.BAD_REQUEST ,"EMAIL already exists");

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "USER Registered Successfully";
    }
}
