package com.example.sl_utilities_provider.repos;

import com.example.sl_utilities_provider.entities.Payment;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long>{

//    Payment findPaymentByCno(String cnumber);
//    List<Payment> findCardByHolderName(String cnumber);

}
