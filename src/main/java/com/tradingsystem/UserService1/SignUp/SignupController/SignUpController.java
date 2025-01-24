package com.tradingsystem.UserService1.SignUp.SignupController;

import com.tradingsystem.UserService1.EmailVerification.EmailVerififcationService;
import com.tradingsystem.UserService1.TraderDTO.TraderDTO;
import com.tradingsystem.UserService1.SignUp.SignupService.TraderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signUp")
public class SignUpController {
    @Autowired
    private TraderService traderService;
    @Autowired
    private EmailVerififcationService emailService;

    @Autowired
    public SignUpController(TraderService traderService, EmailVerififcationService emailService) {
        this.traderService = traderService;
        this.emailService = emailService;
    }
    TraderDTO tempTraderDTO=new TraderDTO();
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createdTrader(@RequestBody TraderDTO traderDTO) {
        if (!traderDTO.getPassword().equals(traderDTO.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
            // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Validate first name (only letters, not empty)
        if (traderDTO.getFirstName() == null || traderDTO.getFirstName().trim().isEmpty()) {
            return new ResponseEntity<>("First name is required and cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (!traderDTO.getFirstName().matches("[a-zA-Z]+")) {
            return new ResponseEntity<>("First name must contain only letters", HttpStatus.BAD_REQUEST);
        }
        // Validate last name (only letters, not empty)
        if (traderDTO.getLastName() == null || traderDTO.getLastName().trim().isEmpty()) {
            return new ResponseEntity<>("Last name is required and cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (!traderDTO.getLastName().matches("[a-zA-Z]+")) {
            return new ResponseEntity<>("Last name must contain only letters", HttpStatus.BAD_REQUEST);
        } // Validate email domain
        if (traderDTO.getEmail() == null || traderDTO.getEmail().trim().isEmpty()) {
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }

        // Extract the domain part and check if it matches allowed providers
        String email = traderDTO.getEmail();
        String[] emailParts = email.split("@");
        if (emailParts.length != 2) {
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }

        String domain = emailParts[1].toLowerCase(); // Get the domain part
        if (!(domain.equals("gmail.com") ||
                domain.equals("outlook.com") ||
                domain.equals("yahoo.com") ||
                domain.equals("icloud.com") ||
                domain.equals("me.com") ||
                domain.equals("geeks4learning.com")||
                domain.equals("mac.com"))) {
            return new ResponseEntity<>("Email must be from Gmail, Outlook, Yahoo, or Apple domains", HttpStatus.BAD_REQUEST);
        }
        if(traderService.existsByEmail(email)==true) {
            return new ResponseEntity<>("User exists!!, please Login or click on the forgot password link", HttpStatus.BAD_REQUEST);
              }
        // Validate password (at least 8 characters, contains uppercase, lowercase, digit, and special character)
        if (traderDTO.getPassword() == null || traderDTO.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        if (!traderDTO.getPassword().matches(passwordRegex)) {
            return new ResponseEntity<>("Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a number, and a special character", HttpStatus.BAD_REQUEST);
        }
        // Check if password and confirmPassword match
        if (!traderDTO.getPassword().equals(traderDTO.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        try {
            emailService.sentOtp(email);
            tempTraderDTO=traderDTO;
            return ResponseEntity.ok("OTP sent to " + email);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send OTP: " + e.getMessage());
        }
    }
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = emailService.vaidateOtp(email, otp);
        if (isValid) {
            traderService.createTrader(tempTraderDTO);
            return ResponseEntity.ok("Email verified successfully!\n User with email: "+email+" successfully Registered!!!");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }




    }

}
