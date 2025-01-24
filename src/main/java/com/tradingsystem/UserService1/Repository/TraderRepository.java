package com.tradingsystem.UserService1.Repository;

import com.tradingsystem.UserService1.Model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraderRepository extends JpaRepository<Trader,Long> {


    Optional<Trader> findByEmail(String email);
}
