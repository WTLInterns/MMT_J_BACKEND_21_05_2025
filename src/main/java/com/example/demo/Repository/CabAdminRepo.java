package com.example.demo.Repository;

import com.example.demo.Model.CabAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
public interface CabAdminRepo extends JpaRepository<CabAdmin, Integer> {
    List<CabAdmin> findByStatus(String status);
}
