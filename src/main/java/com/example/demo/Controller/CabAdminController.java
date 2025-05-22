package com.example.demo.Controller;

import com.example.demo.Model.CabAdmin;
import com.example.demo.Service.CabAdminService;
import com.example.demo.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vendor")
public class CabAdminController {

    @Autowired
    private CabAdminService cabAdminService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCabAdmin(
            @RequestParam("vehicleNameAndRegNo") String vehicleNameAndRegNo,
            @RequestParam("vehicleRcNo") String vehicleRcNo,
            @RequestParam("carOtherDetails") String carOtherDetails,
            @RequestParam("status") String status,

            @RequestParam(value = "vehicleRcImg", required = false) MultipartFile vehicleRcImg,
            @RequestParam(value = "insurance", required = false) MultipartFile insurance,
            @RequestParam(value = "permit", required = false) MultipartFile permit,
            @RequestParam(value = "fitnessCert", required = false) MultipartFile fitnessCert,
            @RequestParam(value = "cabImage", required = false) MultipartFile cabImage,
            @RequestParam(value = "frontImage", required = false) MultipartFile frontImage,
            @RequestParam(value = "backImage", required = false) MultipartFile backImage,
            @RequestParam(value = "sideImage", required = false) MultipartFile sideImage
    ) {
        try {
            CabAdmin cabAdmin = new CabAdmin();
            cabAdmin.setVehicleNameAndRegNo(vehicleNameAndRegNo);
            cabAdmin.setVehicleRcNo(vehicleRcNo);
            cabAdmin.setCarOtherDetails(carOtherDetails);
            cabAdmin.setStatus(status);

            if (vehicleRcImg != null && !vehicleRcImg.isEmpty()) {
                cabAdmin.setVehicleRcImg(cloudinaryService.upload(vehicleRcImg));
            }
            if (insurance != null && !insurance.isEmpty()) {
                cabAdmin.setInsurance(cloudinaryService.upload(insurance));
            }
            if (permit != null && !permit.isEmpty()) {
                cabAdmin.setPermit(cloudinaryService.upload(permit));
            }
            if (fitnessCert != null && !fitnessCert.isEmpty()) {
                cabAdmin.setFitnessCert(cloudinaryService.upload(fitnessCert));
            }
            if (cabImage != null && !cabImage.isEmpty()) {
                cabAdmin.setCabImage(cloudinaryService.upload(cabImage));
            }
            if (frontImage != null && !frontImage.isEmpty()) {
                cabAdmin.setFrontImage(cloudinaryService.upload(frontImage));
            }
            if (backImage != null && !backImage.isEmpty()) {
                cabAdmin.setBackImage(cloudinaryService.upload(backImage));
            }
            if (sideImage != null && !sideImage.isEmpty()) {
                cabAdmin.setSideImage(cloudinaryService.upload(sideImage));
            }

            cabAdminService.saveCabAdmin(cabAdmin);
            return ResponseEntity.ok("Cab added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding cab: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CabAdmin>> getAllVehicles() {
        return ResponseEntity.ok(cabAdminService.getCabAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable int id) {
        return cabAdminService.getCabAdminById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cab not found with ID: " + id));
    }



    @PutMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        try {
            CabAdmin updated = cabAdminService.updateStatus(id, status);
            return ResponseEntity.ok("Cab status updated successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cab/{status}")
    public ResponseEntity<?> getCabsByStatus(@PathVariable String status) {
        List<CabAdmin> cabs = cabAdminService.getCabByStatus(status);
        if (cabs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cabs found with status: " + status);
        }
        return ResponseEntity.ok(cabs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCab(@PathVariable int id) {
        try {
            String result = cabAdminService.deleteCabAdmin(id);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
