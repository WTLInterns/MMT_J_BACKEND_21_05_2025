package com.example.demo.DTO;

public class LoginReponse {
    
    private String email;

    private String role;

    private String jwtToken;

    private String userName;

    public LoginReponse(){
        super();
    }

    public LoginReponse(String email, String role, String jwtToken, String userName) {
        this.email = email;
        this.role = role;
        this.jwtToken = jwtToken;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
