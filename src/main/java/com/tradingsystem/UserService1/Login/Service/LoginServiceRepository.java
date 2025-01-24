package com.tradingsystem.UserService1.Login.Service;

import com.tradingsystem.UserService1.TraderDTO.TraderDTO;

public interface LoginServiceRepository {
    TraderDTO getTraderByEmail(String email);
}
