package edu.duke.ece651.team13.shared.auth;

/**
 * This class is used to organize and send user login
 * information between client side and server side
 */
public class LoginRequest {
    private String email;

    private String password;

    /**
     * Default constructor needed in order that the spring can deserialize this object
     */
    public LoginRequest(){}

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
