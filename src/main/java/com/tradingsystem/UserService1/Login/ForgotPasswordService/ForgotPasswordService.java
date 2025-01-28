package com.tradingsystem.UserService1.Login.ForgotPasswordService;

import com.tradingsystem.UserService1.Login.EmailService.EmailService;
import com.tradingsystem.UserService1.Model.Trader;
import com.tradingsystem.UserService1.Repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service

public class ForgotPasswordService {

    private final TraderRepository traderRepository;


    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
@Autowired
    public ForgotPasswordService(TraderRepository traderRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.traderRepository = traderRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    // Temporary storage for OTPs
    private final Map<String, String> otpStorage = new HashMap<>();

    public String sendOtp(String email) {
        Optional<Trader> traderOptional = traderRepository.findByEmail(email);
        if (traderOptional.isEmpty()) {
            throw new IllegalArgumentException("Email not registered.");
        }

        String otp = generateOtp();
        otpStorage.put(email, otp);
        emailService.sendEmail(email, "Password Recovery OTP", "Your OTP is: " + otp);
        return "OTP sent to email.";
    }

    public String verifyOtp(String email, String otp) {
        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)) {
            otpStorage.remove(email);
            return "OTP verified. Proceed to reset password.";
        } else {
            throw new IllegalArgumentException("Invalid OTP.");
        }
    }

    public String resetPassword(String email, String newPassword) {
        Optional<Trader> traderOptional = traderRepository.findByEmail(email);
        if (traderOptional.isEmpty()) {
            throw new IllegalArgumentException("Email not registered.");
        }
        Trader trader = traderOptional.get();
        //hashing password before saving it to the database
        String hashedPassword=passwordEncoder.encode(newPassword);

        trader.setPassword(hashedPassword); // Add hashing here for security
        traderRepository.save(trader);
        return "Password updated successfully.";
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}

