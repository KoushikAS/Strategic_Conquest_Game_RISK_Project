package edu.duke.ece651.team13.server.auth;

import lombok.AllArgsConstructor;

/**
 * This class is used to organize and send user authentication result
 * between client side and server side
 */
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private String accessToken;
}
