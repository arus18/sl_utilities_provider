package com.example.sl_utilities_provider.repos;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface WorkerRepo extends JpaRepository<Worker, Long> {

    Worker findWorkerByNic(String nic);
    List<Service> findWorkerByName(String name);
}
