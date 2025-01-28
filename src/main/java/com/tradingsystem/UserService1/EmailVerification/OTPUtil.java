package com.tradingsystem.UserService1.EmailVerification;

import java.util.Random;
//generating the Our OTP Using Randomiser

public class OTPUtil {
    public static String generateOTP(int length){
        String digits="0123456789";
        Random random=new Random();
        StringBuilder otp=new StringBuilder(length);
        for(int i=0;i<length;i++){
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }
        return otp.toString();
    }
}
