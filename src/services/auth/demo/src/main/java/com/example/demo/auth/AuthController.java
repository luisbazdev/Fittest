package com.example.demo.auth;

import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import io.jsonwebtoken.Claims;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:8686", "http://localhost:5173"}, allowCredentials = "true")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, JwtTokenUtil jwtTokenUtil){
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/login")
    public Claims logIn(@RequestBody User userBody, HttpServletResponse response){
        String email = userBody.getEmail();

        Optional<User> userQuery = userRepository.findByEmail(email);

        if(userQuery.isPresent()){
            User user = userQuery.get();

            Boolean matches = passwordEncoder.matches(userBody.getPassword(), user.getPassword());

            if(matches){
                String userId = String.valueOf(user.getId());
                String userName = user.getName();
                String userEmail = user.getEmail();
    
                String jwt = jwtTokenUtil.GenerateToken(userId, userName, userEmail);
    
                Cookie cookie = new Cookie("id", jwt);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(1000000);
                cookie.setDomain("localhost");
                cookie.setPath("/");

                response.addCookie(cookie);

                return jwtTokenUtil.getAllClaimsFromToken(jwt);
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User userBody){
        Optional<User> query = userRepository.findByEmail(userBody.getEmail());

        if(query.isPresent()){
            return "Email already taken";
        }
        else{
            userBody.setPassword(passwordEncoder.encode(userBody.getPassword()));
            userRepository.save(userBody);
            return "User created";
        }
    }

    @PostMapping("/info")
    public Claims getUserInfo(@RequestHeader("Authorization") String Authorization){
        String token = Authorization.split(" ", 0)[1];
        return jwtTokenUtil.getAllClaimsFromToken(token);
    }

    @PostMapping("/verify")
    public Boolean authenticate(@RequestBody JwtToken token){
        return jwtTokenUtil.isTokenValid(token.getToken());
    }
}
