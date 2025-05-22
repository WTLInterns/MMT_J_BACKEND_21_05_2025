package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SubAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subAdminFirstName;

    private String subAdminLastName;

    // private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String mobileNo;


    public SubAdmin(){
        super();
    }


    public SubAdmin(int id, String subAdminFirstName, String subAdminLastName, String mobileNo, User user) {
        this.id = id;
        this.subAdminFirstName = subAdminFirstName;
        this.subAdminLastName = subAdminLastName;
        this.mobileNo = mobileNo;
        this.user=user;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getSubAdminFirstName() {
        return subAdminFirstName;
    }


    public void setSubAdminFirstName(String subAdminFirstName) {
        this.subAdminFirstName = subAdminFirstName;
    }


    public String getSubAdminLastName() {
        return subAdminLastName;
    }


    public void setSubAdminLastName(String subAdminLastName) {
        this.subAdminLastName = subAdminLastName;
    }


    


    public String getMobileNo() {
        return mobileNo;
    }


    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    

    

}
