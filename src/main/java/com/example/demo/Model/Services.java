package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isPaymentSuccess;
    private String servicesName;

    public Services() {
        super();
    }

    public Services(int id, String isPaymentSuccess, String servicesName) {
        this.id = id;
        this.isPaymentSuccess = isPaymentSuccess;
        this.servicesName = servicesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsPaymentSuccess() {
        return isPaymentSuccess;
    }

    public void setIsPaymentSuccess(String isPaymentSuccess) {
        this.isPaymentSuccess = isPaymentSuccess;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

}
