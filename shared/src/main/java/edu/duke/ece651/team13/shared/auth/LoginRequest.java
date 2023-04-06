package edu.duke.ece651.team13.shared.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to organize and send user login
 * information between client side and server side
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    private String email;

    private String password;
}
