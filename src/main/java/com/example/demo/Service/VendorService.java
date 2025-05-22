package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.Vendor;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.VendorRepository;

@Service
public class VendorService {
    
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private UserRepository userRepository;



    public Vendor saveVendor(Vendor vendor, String email){
        User user = this.userRepository.findByEmail(email);
        return this.vendorRepository.save(vendor);
    }

    
}
