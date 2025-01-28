package com.tradingsystem.UserService1.Login.ForgotPasswordController;

import com.tradingsystem.UserService1.Login.ForgotPasswordService.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password-recovery")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        return forgotPasswordService.sendOtp(email);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return forgotPasswordService.verifyOtp(email, otp);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        // Validate password (at least 8 characters, contains uppercase, lowercase, digit, and special character)
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        if (!newPassword.matches(passwordRegex)) {
            return new ResponseEntity<>("Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a number, and a special character", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(forgotPasswordService.resetPassword(email, newPassword),HttpStatus.OK);
    }
}
