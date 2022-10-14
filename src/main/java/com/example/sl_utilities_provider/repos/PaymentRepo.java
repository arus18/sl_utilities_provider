package com.example.sl_utilities_provider.repos;

import com.example.sl_utilities_provider.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long>{

}
