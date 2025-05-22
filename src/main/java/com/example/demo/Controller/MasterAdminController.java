package com.example.demo.Controller;


import com.example.demo.Model.Vendor;
import com.example.demo.Service.CloudinaryService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.MasterAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/masterAdmin")
@CrossOrigin(origins = "*")
public class MasterAdminController {

    private final MasterAdminService masterAdminService;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public MasterAdminController(MasterAdminService masterAdminService, CloudinaryService cloudinaryService, EmailService emailService) {
        this.masterAdminService = masterAdminService;
        this.cloudinaryService = cloudinaryService;
        this.emailService = emailService;
    }

    @GetMapping("/allVendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(masterAdminService.getAllVendors(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable int id) {
        Vendor vendor = masterAdminService.getVendorById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (vendor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Vendor not found"));
        }
        return ResponseEntity.ok(generateVendorResponse(vendor));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<?> getVendorByEmail(@PathVariable String email) {
        Vendor vendor = masterAdminService.getVendorByEmail(email);
        if (vendor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Vendor not found"));
        }
        return ResponseEntity.ok(generateVendorResponse(vendor));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        String message = masterAdminService.deleteVendor(id,email);
        if (message.equals("Vendor deleted successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping(
            path = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> addVendor(
            @RequestParam("vendorCompanyName") String vendorCompanyName,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("alternateMobileNo") String alternateMobileNo,
            @RequestParam("city") String city,
            @RequestParam("vendorEmail") String vendorEmail,
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNo") String bankAccountNo,
            @RequestParam("ifscCode") String ifscCode,
            @RequestParam("aadharNo") String aadharNo,
            @RequestParam("panNo") String panNo,
            @RequestParam("udyogAadharNo") String udyogAadharNo,
            @RequestPart(value = "govtApprovalCertificate", required = false) MultipartFile govtApprovalCertificate,
            @RequestPart(value = "vendorDocs", required = false) MultipartFile vendorDocs,
            @RequestPart(value = "vendorImage", required = false) MultipartFile vendorImage,
            @RequestPart(value = "aadharPhoto", required = false) MultipartFile aadharPhoto,
            @RequestPart(value = "panPhoto", required = false) MultipartFile panPhoto,
            @RequestParam(value = "vendorOtherDetails", required = false) String vendorOtherDetails) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        String govtApprovalCertificateUrl = (govtApprovalCertificate != null && !govtApprovalCertificate.isEmpty())
                ? cloudinaryService.upload(govtApprovalCertificate) : null;
        String vendorDocsUrl = (vendorDocs != null && !vendorDocs.isEmpty())
                ? cloudinaryService.upload(vendorDocs) : null;
        String vendorImageUrl = (vendorImage != null && !vendorImage.isEmpty())
                ? cloudinaryService.upload(vendorImage) : null;
        String aadharPhotoUrl = (aadharPhoto != null && !aadharPhoto.isEmpty())
                ? cloudinaryService.upload(aadharPhoto) : null;
        String panPhotoUrl = (panPhoto != null && !panPhoto.isEmpty())
                ? cloudinaryService.upload(panPhoto) : null;

        Vendor vendor = new Vendor();
        vendor.setCompanyName(vendorCompanyName);
        vendor.setContactNo(contactNo);
        vendor.setAlternateMobileNo(alternateMobileNo);
        vendor.setCity(city);
        vendor.setVendorEmail(vendorEmail);
        vendor.setBankName(bankName);
        vendor.setBankAccountNo(bankAccountNo);
        vendor.setIfscCode(ifscCode);
        vendor.setAadharNo(aadharNo);
        vendor.setPanNo(panNo);
        vendor.setUdyogAadharNo(udyogAadharNo);
        vendor.setGovtApprovalCertificate(govtApprovalCertificateUrl);
        vendor.setVendorDocs(vendorDocsUrl);
        vendor.setVendorImage(vendorImageUrl);
        vendor.setAadharPhoto(aadharPhotoUrl);
        vendor.setPanPhoto(panPhotoUrl);
        vendor.setVendorOtherDetails(vendorOtherDetails);

        Vendor savedVendor = masterAdminService.saveVendor(vendor, email);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vendor added successfully");
        response.put("vendor", savedVendor);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping(
            path = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> updateVendor(
            @PathVariable("id") int id,
            @RequestParam(value = "vendorCompanyName", required = false) String vendorCompanyName,
            @RequestParam(value = "contactNo", required = false) String contactNo,
            @RequestParam(value = "alternateMobileNo", required = false) String alternateMobileNo,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "vendorEmail", required = false) String vendorEmail,
            @RequestParam(value = "bankName", required = false) String bankName,
            @RequestParam(value = "bankAccountNo", required = false) String bankAccountNo,
            @RequestParam(value = "ifscCode", required = false) String ifscCode,
            @RequestParam(value = "aadharNo", required = false) String aadharNo,
            @RequestParam(value = "panNo", required = false) String panNo,
            @RequestParam(value = "udyogAadharNo", required = false) String udyogAadharNo,
            @RequestPart(value = "govtApprovalCertificate", required = false) MultipartFile govtApprovalCertificate,
            @RequestPart(value = "vendorDocs", required = false) MultipartFile vendorDocs,
            @RequestPart(value = "vendorImage", required = false) MultipartFile vendorImage,
            @RequestPart(value = "aadharPhoto", required = false) MultipartFile aadharPhoto,
            @RequestPart(value = "panPhoto", required = false) MultipartFile panPhoto,
            @RequestParam(value = "vendorOtherDetails", required = false) String vendorOtherDetails
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        try {
            String govtApprovalCertificateUrl = (govtApprovalCertificate != null && !govtApprovalCertificate.isEmpty())
                    ? cloudinaryService.upload(govtApprovalCertificate) : null;
            String vendorDocsUrl = (vendorDocs != null && !vendorDocs.isEmpty())
                    ? cloudinaryService.upload(vendorDocs) : null;
            String vendorImageUrl = (vendorImage != null && !vendorImage.isEmpty())
                    ? cloudinaryService.upload(vendorImage) : null;
            String aadharPhotoUrl = (aadharPhoto != null && !aadharPhoto.isEmpty())
                    ? cloudinaryService.upload(aadharPhoto) : null;
            String panPhotoUrl = (panPhoto != null && !panPhoto.isEmpty())
                    ? cloudinaryService.upload(panPhoto) : null;

            Vendor updatedVendor = masterAdminService.updateVendor(
                    id, vendorCompanyName, contactNo, alternateMobileNo, city,
                    vendorEmail, bankName, bankAccountNo, ifscCode,
                    aadharNo, panNo, udyogAadharNo, govtApprovalCertificateUrl,
                    vendorDocsUrl, vendorImageUrl, aadharPhotoUrl,
                    panPhotoUrl, vendorOtherDetails, email
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vendor updated successfully");
            response.put("vendor", generateVendorResponse(updatedVendor));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while updating vendor: " + e.getMessage());
        }
    }

    @PostMapping("/send-manual/{email}")
    public ResponseEntity<?> sendManualEmailWithPath(@PathVariable("email") String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        try {
            String subject = "Vendor Registration Form - World Trip Link";

            String emailContent = "<html><body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                    "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #eee; border-radius: 5px;'>" +
                    "<div style='text-align: center; margin-bottom: 20px;'>" +
                    "<img src='https://your-company-logo-url.com/logo.png' alt='WTL Logo' style='max-width: 150px;'>" +
                    "</div>" +
                    "<h2 style='color: #1e88e5;'>Vendor Registration Request</h2>" +
                    "<p>Hello,</p>" +
                    "<p>You have been invited to register as a vendor with World Trip Link. Please complete the registration process by clicking on the link below:</p>" +
                    "<p style='text-align: center;'>" +
                    "<a href='https://admin.worldtriplink.in/vendor-registration' " +
                    "style='display: inline-block; padding: 10px 20px; background-color: #1e88e5; color: white; " +
                    "text-decoration: none; border-radius: 4px; font-weight: bold;'>Complete Vendor Registration</a>" +
                    "</p>" +
                    "<p>If the button above doesn't work, you can copy and paste this link into your browser:</p>" +
                    "<p style='background-color: #f5f5f5; padding: 10px; border-radius: 4px;'>" +
                    "<a href='https://admin.worldtriplink.in/vendor-registration'>https://admin.worldtriplink.in/vendor-registration</a>" +
                    "</p>" +
                    "<p>Please complete the registration at your earliest convenience. If you have any questions, please contact our support team.</p>" +
                    "<p>Thank you,</p>" +
                    "<p><strong>WTL Tourism Pvt. Ltd.</strong><br>" +
                    "Email: support@worldtriplink.in<br>" +
                    "Phone: +91 1234567890</p>" +
                    "</div>" +
                    "</body></html>";


            boolean emailSent = emailService.sendEmail(emailContent, subject, email);

            if (emailSent) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Vendor registration link sent successfully to " + email);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send email. Please try again later.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getMessage());
        }
    }

    @PostMapping("/send-email/{email}")
    public ResponseEntity<?> sendEmail(@PathVariable("email") String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        try {
            String subject = "Vendor Registration Form";

            String emailContent = "<html><body>" +
                    "<h2>Vendor Registration Request</h2>" +
                    "<p>Hello,</p>" +
                    "<p>You have been invited to register as a vendor. Please complete the registration by clicking the link below:</p>" +
                    "<p><a href='http://localhost:3000/vendor-registration'>Complete Vendor Registration</a></p>" +
                    "<p>If the link doesn't work, copy and paste this URL in your browser: http://localhost:3000/vendor-registration</p>" +
                    "<p>Thank you,<br>Admin</p>" +
                    "</body></html>";

            boolean emailSent = emailService.sendEmail(emailContent, subject, email);

            if (emailSent) {
                return ResponseEntity.ok("Email sent successfully to " + email);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send email. Please try again later.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getMessage());
        }
    }

    private Map<String, Object> generateVendorResponse(Vendor vendor) {
        Map<String, Object> response = new HashMap<>();

        response.put("id", vendor.getId());
        response.put("vendorCompanyName", vendor.getCompanyName());
        response.put("contactNo", vendor.getContactNo());
        response.put("alternateMobileNo", vendor.getAlternateMobileNo());
        response.put("city", vendor.getCity());
        response.put("vendorEmail", vendor.getVendorEmail());

        Map<String, Object> bankingDetails = new HashMap<>();
        bankingDetails.put("bankName", vendor.getBankName());
        bankingDetails.put("accountNo", vendor.getBankAccountNo());
        bankingDetails.put("ifscCode", vendor.getIfscCode());
        response.put("bankingDetails", bankingDetails);

        Map<String, Object> identityDocuments = new HashMap<>();
        identityDocuments.put("aadharNo", vendor.getAadharNo());
        identityDocuments.put("panNo", vendor.getPanNo());
        identityDocuments.put("udyogAadharNo", vendor.getUdyogAadharNo());
        response.put("identityDocuments", identityDocuments);

        Map<String, String> documentUrls = new HashMap<>();
        if (vendor.getGovtApprovalCertificate() != null) {
            documentUrls.put("govtApprovalCertificate", vendor.getGovtApprovalCertificate());
        }
        if (vendor.getVendorDocs() != null) {
            documentUrls.put("vendorDocs", vendor.getVendorDocs());
        }
        if (vendor.getVendorImage() != null) {
            documentUrls.put("vendorImage", vendor.getVendorImage());
        }
        if (vendor.getAadharPhoto() != null) {
            documentUrls.put("aadharPhoto", vendor.getAadharPhoto());
        }
        if (vendor.getPanPhoto() != null) {
            documentUrls.put("panPhoto", vendor.getPanPhoto());
        }
        response.put("documents", documentUrls);

        response.put("otherDetails", vendor.getVendorOtherDetails());

        return response;
    }
}