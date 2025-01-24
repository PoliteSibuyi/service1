package com.tradingsystem.UserService1.SignUp.SignupService;

import com.tradingsystem.UserService1.Model.Trader;
import com.tradingsystem.UserService1.TraderDTO.TraderDTO;
import com.tradingsystem.UserService1.Repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderService implements SignUpServiceInterface {

    private TraderRepository traderRepository;
    @Autowired
    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }
    @Override
    public TraderDTO createTrader(TraderDTO traderDTO) {
        Trader trader=new Trader();
        trader.setEmail(traderDTO.getEmail());
        trader.setFirstName(traderDTO.getFirstName());
        trader.setLastName(traderDTO.getLastName());
        trader.setPassword(traderDTO.getPassword());
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
