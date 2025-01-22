package com.tradingsystem.UserService1.EmailVerification;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/v1/verification")
    public class VerificationController {

        @Autowired
        private EmailVerififcationService service;

        @PostMapping("/send-otp")
        public ResponseEntity<String> sendOtp(@RequestParam String email) {
            try {
                service.sentOtp(email);
                return ResponseEntity.ok("OTP sent to " + email);
            } catch (MessagingException e) {
                return ResponseEntity.status(500).body("Failed to send OTP: " + e.getMessage());
            }
        }
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = service.vaidateOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok("Email verified successfully!");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }
    }
}
