package edu.duke.ece651.team13.shared.auth;

/**
 * This class is used to organize and send user registration
 * information between client side and server side
 */
public class RegisterRequest {
    private String fullName;

    private String email;

    private String password;

    /**
     * Default constructor needed in order that the spring can deserialize this object
     */
    public RegisterRequest(){}

    public RegisterRequest(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
