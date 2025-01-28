package com.tradingsystem.UserService1.SignUp.SignupService;

import com.tradingsystem.UserService1.TraderDTO.TraderDTO;

public interface SignUpServiceInterface {
    TraderDTO createTrader(TraderDTO traderDTO);

    boolean existsByEmail(String email);
}
