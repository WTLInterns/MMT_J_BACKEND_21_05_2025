package com.example.demo.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;
    private String contactNo;
    private String alternateMobileNo;
    private String city;
    private String vendorEmail;
    private String bankName;
    private String bankAccountNo;
    private String ifscCode;
    private String aadharNo;
    private String panNo;
    private String udyogAadharNo;
    private String govtApprovalCertificate;
    private String vendorDocs;
    private String vendorImage;
    private String aadharPhoto;
    private String panPhoto;
    private String vendorOtherDetails;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Vendor(int id, String companyName, String contactNo, String alternateMobileNo, String city,
                  String vendorEmail, String bankName, String bankAccountNo, String ifscCode, String aadharNo, String panNo,
                  String udyogAadharNo, String govtApprovalCertificate, String vendorDocs, String vendorImage,
                  String aadharPhoto, String panPhoto, String vendorOtherDetails, String password, User user){
        this.id = id;
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.alternateMobileNo = alternateMobileNo;
        this.city = city;
        this.vendorEmail = vendorEmail;
        this.bankName = bankName;
        this.bankAccountNo = bankAccountNo;
        this.ifscCode = ifscCode;
        this.aadharNo = aadharNo;
        this.panNo = panNo;
        this.udyogAadharNo = udyogAadharNo;
        this.govtApprovalCertificate = govtApprovalCertificate;
        this.vendorDocs = vendorDocs;
        this.vendorImage = vendorImage;
        this.aadharPhoto = aadharPhoto;
        this.panPhoto = panPhoto;
        this.vendorOtherDetails = vendorOtherDetails;
        this.password = password;
        this.user=user;

    }
    public Vendor(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcompanyName() {
        return companyName;
    }

    public void setcompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    public void setAlternateMobileNo(String alternateMobileNo) {
        this.alternateMobileNo = alternateMobileNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getUdyogAadharNo() {
        return udyogAadharNo;
    }

    public void setUdyogAadharNo(String udyogAadharNo) {
        this.udyogAadharNo = udyogAadharNo;
    }

    public String getGovtApprovalCertificate() {
        return govtApprovalCertificate;
    }

    public void setGovtApprovalCertificate(String govtApprovalCertificate) {
        this.govtApprovalCertificate = govtApprovalCertificate;
    }

    public String getVendorDocs() {
        return vendorDocs;
    }

    public void setVendorDocs(String vendorDocs) {
        this.vendorDocs = vendorDocs;
    }

    public String getVendorImage() {
        return vendorImage;
    }

    public void setVendorImage(String vendorImage) {
        this.vendorImage = vendorImage;
    }

    public String getAadharPhoto() {
        return aadharPhoto;
    }

    public void setAadharPhoto(String aadharPhoto) {
        this.aadharPhoto = aadharPhoto;
    }

    public String getPanPhoto() {
        return panPhoto;
    }

    public void setPanPhoto(String panPhoto) {
        this.panPhoto = panPhoto;
    }

    public String getVendorOtherDetails() {
        return vendorOtherDetails;
    }

    public void setVendorOtherDetails(String vendorOtherDetails) {
        this.vendorOtherDetails = vendorOtherDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
