package com.example.demo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CabAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String vehicleNameAndRegNo;
    private String vehicleRcNo;
    private String carOtherDetails;
    private String status;


    private String vehicleRcImg;
    private String insurance;
    private String permit; 
    private String fitnessCert; 
    private String cabImage;
    private String frontImage; 
    private String backImage; 
    private String sideImage; 
    

    public CabAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CabAdmin(int id, String vehicleNameAndRegNo, String vehicleRcNo, String carOtherDetails,
                    String vehicleRcImg, String insurance, String permit, String fitnessCert, String cabImage,
                    String frontImage, String backImage, String sideImage, String status) {
        super();
        this.id = id;
        this.vehicleNameAndRegNo = vehicleNameAndRegNo;
        this.vehicleRcNo = vehicleRcNo;
        this.carOtherDetails = carOtherDetails;
        this.vehicleRcImg = vehicleRcImg;
        this.insurance = insurance;
        this.permit = permit;
        this.fitnessCert = fitnessCert;
        this.cabImage = cabImage;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.sideImage = sideImage;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNameAndRegNo() {
        return vehicleNameAndRegNo;
    }

    public void setVehicleNameAndRegNo(String vehicleNameAndRegNo) {
        this.vehicleNameAndRegNo = vehicleNameAndRegNo;
    }

    public String getVehicleRcNo() {
        return vehicleRcNo;
    }

    public void setVehicleRcNo(String vehicleRcNo) {
        this.vehicleRcNo = vehicleRcNo;
    }

    public String getCarOtherDetails() {
        return carOtherDetails;
    }

    public void setCarOtherDetails(String carOtherDetails) {
        this.carOtherDetails = carOtherDetails;
    }

    public String getVehicleRcImg() {
        return vehicleRcImg;
    }

    public void setVehicleRcImg(String vehicleRcImg) {
        this.vehicleRcImg = vehicleRcImg;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getFitnessCert() {
        return fitnessCert;
    }

    public void setFitnessCert(String fitnessCert) {
        this.fitnessCert = fitnessCert;
    }

    public String getCabImage() {
        return cabImage;
    }

    public void setCabImage(String cabImage) {
        this.cabImage = cabImage;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getSideImage() {
        return sideImage;
    }

    public void setSideImage(String sideImage) {
        this.sideImage = sideImage;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



