package com.example.demo.auth;

import com.example.demo.jwt.JwtToken;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import io.jsonwebtoken.Claims;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AuthController(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, RabbitTemplate rabbitTemplate){
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/login")
    public Map<String, Object> logIn(@RequestBody User userBody, HttpServletResponse response){
        Map<String, Object> data = new HashMap<String, Object>();

        String email = userBody.getEmail();

        Optional<User> userQuery = userRepository.findByEmail(email);

        if(userQuery.isPresent()){
            User user = userQuery.get();

            Boolean matches = passwordEncoder.matches(userBody.getPassword(), user.getPassword());

            if(matches){
                data.put("message", "Succesfully logged in");
                data.put("authenticated", true);

                String userId = String.valueOf(user.getId());
                String userName = user.getName();
                String userEmail = user.getEmail();
		    
                String jwt = jwtTokenUtil.GenerateToken(userId, userName, userEmail);
                data.put("session", jwtTokenUtil.getAllClaimsFromToken(jwt));
                
                Cookie cookie = new Cookie("id", jwt);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(1000000);
                cookie.setDomain("localhost");
                cookie.setPath("/");

                response.addCookie(cookie);

            }
            else{
                data.put("message", "Invalid credentials");
                data.put("authenticated", false);
                data.put("session", null);
            }
        }

        return data;
    }

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody User userBody){
        Map<String, Object> data = new HashMap<String, Object>();

        Optional<User> query = userRepository.findByEmail(userBody.getEmail());

        if(query.isPresent()){
            data.put("message", "Email is already taken");
            data.put("user", null);
        }
        else{
            data.put("message", "Successfully signed up");
            data.put("user", userBody);
            userBody.setPassword(passwordEncoder.encode(userBody.getPassword()));
            User user = userRepository.save(userBody);
            rabbitTemplate.convertAndSend("fittest", "signup.#asd", user.getId());
        }

        return data;
    }

    @PostMapping("/logout")
    public Map<String, Object> logOut(HttpServletResponse response){
        Cookie cookie = new Cookie("id", null);
	    cookie.setHttpOnly(true);
	    cookie.setMaxAge(0);
	    cookie.setDomain("localhost");
	    cookie.setPath("/");

	    response.addCookie(cookie);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("message", "Succesfully logged out");
        data.put("authenticated", false);
        data.put("session", null);
        
	    return data;
    }

    @PostMapping("/status")
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String Authorization){
        Map<String, Object> data = new HashMap<String, Object>();

        String token = Authorization.split(" ", 0)[1];

        if(jwtTokenUtil.isTokenValid(token)){
            data.put("authenticated", true);
            data.put("session", jwtTokenUtil.getAllClaimsFromToken(token));
        }
        else{
            data.put("authenticated", false);
            data.put("session", null);
        }

        return data;
    }

    @PostMapping("/verify")
    public Map<String, Object> authenticate(@RequestBody JwtToken token){
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("token", token);
        data.put("valid", jwtTokenUtil.isTokenValid(token.getToken()));

        return data;
    }
}
