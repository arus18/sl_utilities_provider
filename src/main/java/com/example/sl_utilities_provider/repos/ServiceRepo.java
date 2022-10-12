package com.example.sl_utilities_provider.repos;
import com.example.sl_utilities_provider.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Long> {
    Service findServiceByName(String name);
    Service findServiceByCategory(String category);
}
