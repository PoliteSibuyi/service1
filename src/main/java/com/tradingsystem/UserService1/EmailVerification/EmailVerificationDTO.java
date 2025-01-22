package com.tradingsystem.UserService1.EmailVerification;



import java.time.LocalDateTime;
public class EmailVerificationDTO {

    private Long id;
    private String email;
    private String otp;
    private LocalDateTime expirationTime;
    public EmailVerificationDTO() {
    }
    public EmailVerificationDTO( String email, String otp, LocalDateTime expirationTime) {

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


