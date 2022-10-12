package com.example.sl_utilities_provider.repos;

import com.example.sl_utilities_provider.entities.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepo extends JpaRepository<Inquiry, Long> {
    Inquiry findInquiryByInic(String inic);


}
