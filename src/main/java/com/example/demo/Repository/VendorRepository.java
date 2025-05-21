package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Vendor;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{

    Optional<Vendor> findByCompanyName(String companyName);

    Vendor findByVendorEmail(String vendorEmail);

    Optional<Vendor> findById(int vendorId);

}
