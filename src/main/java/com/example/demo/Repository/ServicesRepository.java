package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Services;

@Repository
public interface ServicesRepository extends  JpaRepository<Services, Integer>{
    
}
