package com.tradingsystem.UserService1.Login.Service;

import com.tradingsystem.UserService1.Exceptions.TraderNotFoundException;
import com.tradingsystem.UserService1.Model.Trader;
import com.tradingsystem.UserService1.Repository.TraderRepository;
import com.tradingsystem.UserService1.TraderDTO.TraderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceRepository {
private TraderRepository traderRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public LoginService(TraderRepository traderRepository,PasswordEncoder passwordEncoder) {
        this.traderRepository = traderRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public TraderDTO getTraderByEmail(String email) {
        Trader trader=traderRepository.findByEmail(email).orElseThrow(()-> new TraderNotFoundException("User is not found"));
        return mapToDTO(trader);
    }
    //MAPPING THE DTO TO THE ENTITY
    public TraderDTO mapToDTO(Trader trader){
        TraderDTO traderDTO=new TraderDTO();
        traderDTO.setId(trader.getId());
        traderDTO.setEmail(trader.getEmail());
        traderDTO.setFirstName(trader.getFirstName());
        traderDTO.setLastName(trader.getFirstName());
        traderDTO.setPassword(trader.getPassword());
        traderDTO.setDateCreated(trader.getDateCreated());
        return  traderDTO;

    }
    //MAPPING THE ENTITY TO THE DTO
    public Trader mapToDTO(TraderDTO traderDTO){
        Trader trader=new Trader();
        trader.setId(traderDTO.getId());
        trader.setEmail(traderDTO.getEmail());
        trader.setFirstName(traderDTO.getFirstName());
        trader.setLastName(traderDTO.getFirstName());
        trader.setPassword(traderDTO.getPassword());
        trader.setDateCreated(traderDTO.getDateCreated());
        return  trader;

    }
    // Method to verify the password
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
