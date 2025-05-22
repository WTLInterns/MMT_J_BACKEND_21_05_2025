package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.MasterAdmin;
import org.springframework.stereotype.Repository;


@Repository
public interface MasterAdminRepository extends JpaRepository<MasterAdmin, Integer>{
    
}
