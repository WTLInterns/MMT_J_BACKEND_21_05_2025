package com.example.demo.Service;

import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.JwtConfig.JwtUtils;
import com.example.demo.Repository.DriverRepository;
import com.example.demo.Repository.MasterAdminRepository;
import com.example.demo.Repository.SubAdminRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.VendorRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private SubAdminRepository subAdminRepository;

    @Autowired
    private MasterAdminRepository masterAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;



// regsiter
    public ResponseEntity<?> register(RegisterRequest request) {
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Password cannot be null or blank");
        }

        User existingUser = userRepository.findByEmail(request.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User user = new User();
        user.setUserName(request.getUsername());
        user.setEmail(request.getUsername());
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);


        switch (request.getRole()) {
            case "VENDOR" -> {
                Vendor vendor = new Vendor();
                vendor.setCompanyName(request.getCompanyName());
                vendor.setUser(user);
                vendorRepository.save(vendor);
            }
            case "DRIVER" -> {
                Driver driver = new Driver();
                driver.setLicenseNumber(request.getLicenseNumber());
                driver.setUser(user);
                driverRepository.save(driver);
            }
            case "SUB_ADMIN"->{
                SubAdmin subAdmin = new SubAdmin();
                subAdmin.setSubAdminFirstName(request.getSubAdminFirstName());
                subAdmin.setSubAdminLastName(request.getSubAdminLastName());
                subAdmin.setMobileNo(request.getSubAdminPhoneNo());
                subAdmin.setUser(user);
                subAdminRepository.save(subAdmin);
                }
            case "MASTER_ADMIN"->{
                MasterAdmin masterAdmin = new MasterAdmin();
                masterAdmin.setFirstName(request.getMasterAdminFirstName());
                masterAdmin.setLastName(request.getMasterAdminLastName());
                masterAdmin.setPhoneNo(request.getMasterAdminPhone());
                masterAdmin.setUser(user);
                masterAdminRepository.save(masterAdmin);
            }

        }

        return ResponseEntity.ok("User Registered "+ user.getEmail());
    }



    // login

    public LoginRequest login(LoginRequest loginRequest) {
		LoginRequest response = new LoginRequest();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
			var user = this.userRepository.findByEmail(loginRequest.getEmail());
			var jwt = jwtUtils.generateToken(user);
			response.setToken(jwt);
			response.setRole(user.getRole());
			response.setEmail(user.getEmail());
			response.setPassword(user.getPassword());
		}
		catch(Exception e){
		System.out.println(e.getMessage());
			}
		return response;
	}

}
