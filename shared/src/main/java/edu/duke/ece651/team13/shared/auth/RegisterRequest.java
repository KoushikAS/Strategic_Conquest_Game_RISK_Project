package edu.duke.ece651.team13.shared.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to organize and send user registration
 * information between client side and server side
 */
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String fullName;

    private String email;

    private String password;
}
