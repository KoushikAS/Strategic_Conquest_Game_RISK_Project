package edu.duke.ece651.team13.server.controller;

import edu.duke.ece651.team13.server.entity.UserEntity;
import edu.duke.ece651.team13.server.security.JwtTokenUtil;
import edu.duke.ece651.team13.server.service.UserService;
import edu.duke.ece651.team13.shared.auth.LoginRequest;
import edu.duke.ece651.team13.shared.auth.LoginResponse;
import edu.duke.ece651.team13.shared.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the controller for authentication - login and register features
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    final BCryptPasswordEncoder passwordEncoder;

    /**
     * Registers a new user with the given user input.
     *
     * @param registerRequest the user input containing the full name, email, and password of the user.
     * @return a ResponseEntity with a success message and the ID of the registered user if successful,
     *         or an error message if the user already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        // check if the user already exists in the database
        String existResult = userService.isUserPresent(registerRequest);
        if (existResult != null) {
            // user already exists, return an error response
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        // register the new user
        UserEntity savedUser = userService.createUser(registerRequest.getFullName(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()));

        // create a response map with the success message and user ID
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", savedUser.getId());

        // return a success response
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            UserEntity user = (UserEntity) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            LoginResponse response = new LoginResponse(user.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
