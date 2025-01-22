package com.tradingsystem.UserService1.EmailVerification;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String otp;  @Column(nullable = false)

    private LocalDateTime expirationTime;

    public EmailVerification() {
    }

    public EmailVerification( String email, String otp, LocalDateTime expirationTime) {

        this.email = email;
        this.otp = otp;
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
