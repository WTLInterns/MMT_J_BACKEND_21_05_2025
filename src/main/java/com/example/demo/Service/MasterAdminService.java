package com.example.demo.Service;


import com.example.demo.Exception.EmailAlreadyExistsException;
import com.example.demo.Exception.VendorNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Model.Vendor;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterAdminService {

    private final VendorRepository vendorRepository;

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    public MasterAdminService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    public Vendor saveVendor(Vendor vendor, String email) {
        User user = userRepository.findByEmail(email);

        Vendor existingVendor = vendorRepository.findByVendorEmail(vendor.getVendorEmail());
        if (existingVendor != null) {
            throw new EmailAlreadyExistsException("Vendor with this email already exists");
        }

        vendor.setPassword("Vendor@123");

        return vendorRepository.save(vendor);
    }


    public List<Vendor> getAllVendors(String email) {
        User user = this.userRepository.findByEmail(email);
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(int id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public Vendor getVendorByCompanyName(String companyName) {
        return vendorRepository.findByCompanyName(companyName).orElse(null);
    }

    public Vendor getVendorByEmail(String email) {
        return vendorRepository.findByVendorEmail(email);
    }

    public String deleteVendor(int id,String email) {
        User user = this.userRepository.findByEmail(email);
        if (vendorRepository.existsById(id)) {
            vendorRepository.deleteById(id);
            return "Vendor deleted successfully";
        }
        return "Vendor not found";
    }

    public Vendor updateVendor(int id, String companyName, String contactNo, String alternateMobileNo,
                               String city, String vendorEmail, String bankName, String bankAccountNo,
                               String ifscCode, String aadharNo, String panNo, String udyogAadharNo,
                               String govtApprovalCertificateUrl, String vendorDocsUrl, String vendorImageUrl,
                               String aadharPhotoUrl, String panPhotoUrl, String vendorOtherDetails, String email) {

        User user = this.userRepository.findByEmail(email);

        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));

        if (companyName != null) existingVendor.setCompanyName(companyName);
        if (contactNo != null) existingVendor.setContactNo(contactNo);
        if (alternateMobileNo != null) existingVendor.setAlternateMobileNo(alternateMobileNo);
        if (city != null) existingVendor.setCity(city);
        if (vendorEmail != null) existingVendor.setVendorEmail(vendorEmail);
        if (bankName != null) existingVendor.setBankName(bankName);
        if (bankAccountNo != null) existingVendor.setBankAccountNo(bankAccountNo);
        if (ifscCode != null) existingVendor.setIfscCode(ifscCode);
        if (aadharNo != null) existingVendor.setAadharNo(aadharNo);
        if (panNo != null) existingVendor.setPanNo(panNo);
        if (udyogAadharNo != null) existingVendor.setUdyogAadharNo(udyogAadharNo);
        if (govtApprovalCertificateUrl != null) existingVendor.setGovtApprovalCertificate(govtApprovalCertificateUrl);
        if (vendorDocsUrl != null) existingVendor.setVendorDocs(vendorDocsUrl);
        if (vendorImageUrl != null) existingVendor.setVendorImage(vendorImageUrl);
        if (aadharPhotoUrl != null) existingVendor.setAadharPhoto(aadharPhotoUrl);
        if (panPhotoUrl != null) existingVendor.setPanPhoto(panPhotoUrl);
        if (vendorOtherDetails != null) existingVendor.setVendorOtherDetails(vendorOtherDetails);

        return vendorRepository.save(existingVendor);
    }

}
