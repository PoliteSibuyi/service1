package com.tradingsystem.UserService1.login.Service;

import lombok.Data;

@Data
public class loginService {
 private String a;
 private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
