package com.tradingsystem.UserService1.SignUp.SignupService;

import com.tradingsystem.UserService1.Model.Trader;
import com.tradingsystem.UserService1.TraderDTO.TraderDTO;
import com.tradingsystem.UserService1.Repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TraderService implements SignUpServiceInterface {

    private TraderRepository traderRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public TraderService(TraderRepository traderRepository,PasswordEncoder passwordEncoder) {
        this.traderRepository = traderRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public TraderDTO createTrader(TraderDTO traderDTO) {
        Trader trader=new Trader();
        trader.setEmail(traderDTO.getEmail());
        trader.setFirstName(traderDTO.getFirstName());
        trader.setLastName(traderDTO.getLastName());
        //trader.setPassword(traderDTO.getPassword());
        //hashing password using encode method from spring security
        trader.setPassword(passwordEncoder.encode(traderDTO.getPassword()));
        trader.setDateCreated(traderDTO.getDateCreated());
        traderRepository.save(trader);

        TraderDTO traderResponse=new TraderDTO();
        traderResponse.setId(trader.getId());
        traderResponse.setEmail(trader.getEmail());
        traderResponse.setFirstName(trader.getFirstName());
        traderResponse.setLastName(trader.getLastName());
        traderResponse.setPassword(trader.getPassword());
        traderResponse.setDateCreated(trader.getDateCreated());

        return traderResponse;
    }

    @Override
    public boolean existsByEmail(String email) {
    return traderRepository.findByEmail(email).isPresent();


    }
}
