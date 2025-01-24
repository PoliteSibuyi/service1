package com.tradingsystem.UserService1.Login.Controller;

import com.tradingsystem.UserService1.TraderDTO.TraderDTO;
import com.tradingsystem.UserService1.Login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")

public class LoginController {
    private LoginService loginService;
@Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public ResponseEntity<?>getTraderByEmail(@RequestParam String email,@RequestParam String password){
        TraderDTO traderDTO=loginService.getTraderByEmail(email);
        if(traderDTO.equals(null)){
            return new ResponseEntity<>("User does not Exist.Please sign up",HttpStatus.BAD_REQUEST);
        }
        if(!traderDTO.getPassword().equals(password)){
            return new ResponseEntity<>("Login UnSuccessful. Please enter correct password, or reset Password", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("User with username/ email: "+traderDTO.getEmail()+" \nsuccessfully Logged in",HttpStatus.OK);


    }
}
