package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.MasterAdmin;

public interface MasterAdminRepository extends JpaRepository<MasterAdmin, Integer>{
    
}
