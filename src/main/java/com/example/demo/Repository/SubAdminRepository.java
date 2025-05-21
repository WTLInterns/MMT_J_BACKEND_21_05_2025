package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.SubAdmin;

@Repository
public interface SubAdminRepository  extends JpaRepository<SubAdmin, Integer>{
    
}
