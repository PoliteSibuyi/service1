package com.tradingsystem.UserService1.EmailVerification;

import com.tradingsystem.UserService1.SignUp.SignupService.TraderService;
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

    private final TraderService traderService;
    private final EmailVerififcationService emailService;

    @Autowired
    public VerificationController(TraderService traderService, EmailVerififcationService emailService) {
        this.traderService = traderService;
        this.emailService = emailService;
    }

//sending the OTP
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        try {
            emailService.sentOtp(email);
            return ResponseEntity.ok("OTP sent to " + email);
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send OTP: " + e.getMessage());
        }
    }

}
