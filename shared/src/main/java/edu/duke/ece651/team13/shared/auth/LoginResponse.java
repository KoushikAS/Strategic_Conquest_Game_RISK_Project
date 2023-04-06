package edu.duke.ece651.team13.shared.auth;

/**
 * This class is used to organize and send user authentication result
 * between client side and server side
 */
public class LoginResponse {
    private String email;
    private String accessToken;

    public LoginResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
