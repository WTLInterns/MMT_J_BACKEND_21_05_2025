package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    public User  findByEmail(String email);

    boolean existsByEmail(String email);


}
