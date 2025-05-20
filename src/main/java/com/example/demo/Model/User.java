package com.example.demo.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int userId;

private String userName;

private String email;

private String password;

private String role;

@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Vendor vendor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Driver driver;


    

    public User(int userId, String email, String password, String role, Vendor vendor, Driver driver, String userName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.vendor = vendor;
        this.driver = driver;
        this.userName=userName;
    }


    public User(){
        super();
    }


    

    public int getUserId() {
        return userId;
    }




    public void setUserId(int userId) {
        this.userId = userId;
    }

    




    public String getEmail() {
        return email;
    }




    public void setEmail(String email) {
        this.email = email;
    }




    public void setPassword(String password) {
        this.password = password;
    }




    public String getRole() {
        return role;
    }




    public void setRole(String role) {
        this.role = role;
    }




    public Vendor getVendor() {
        return vendor;
    }




    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }




    public Driver getDriver() {
        return driver;
    }




    public void setDriver(Driver driver) {
        this.driver = driver;
    }




    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

    @Override
    public String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
       return this.email;
    }




    public String getUserName() {
        return userName;
    }




    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}