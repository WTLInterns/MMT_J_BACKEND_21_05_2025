package com.example.demo.Service;

import com.example.demo.Model.CabAdmin;
import com.example.demo.Repository.CabAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CabAdminService {

    @Autowired
    private CabAdminRepo cabAdminRepository;

    public CabAdmin saveCabAdmin(CabAdmin cabAdmin) {
        return cabAdminRepository.save(cabAdmin);
    }

    public List<CabAdmin> getCabAdmin() {
        return cabAdminRepository.findAll();
    }

    public Optional<CabAdmin> getCabAdminById(int id) {
        return cabAdminRepository.findById(id);
    }

    public CabAdmin updateStatus(int id, String status) {
        CabAdmin cabAdmin = cabAdminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cab not found with id: " + id));
        cabAdmin.setStatus(status);
        return cabAdminRepository.save(cabAdmin);
    }

    public List<CabAdmin> getCabByStatus(String status) {
        return cabAdminRepository.findByStatus(status);
    }

    public String deleteCabAdmin(int id) {
        CabAdmin cab = cabAdminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cab not found with id: " + id));
        cabAdminRepository.delete(cab);
        return "Cab deleted successfully";
    }
}
