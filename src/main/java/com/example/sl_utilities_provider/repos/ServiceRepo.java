package com.example.sl_utilities_provider.repos;
import com.example.sl_utilities_provider.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Long> {
    List<Service> findServiceByName(String name);
    List<Service> findServiceByCategory(String category);
}
