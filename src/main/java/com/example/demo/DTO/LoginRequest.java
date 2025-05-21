package com.example.demo.DTO;

public class LoginRequest {
    private String email;

    private String password;

    private String token;

    private String role;

    public LoginRequest(){
        super();
    }

    public LoginRequest(String email, String password, String token, String role) {
        this.email = email;
        this.password = password;
        this.token=token;
        this.role=role;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    
}
