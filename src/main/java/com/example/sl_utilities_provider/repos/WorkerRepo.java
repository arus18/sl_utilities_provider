package com.example.sl_utilities_provider.repos;
import com.example.sl_utilities_provider.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepo extends JpaRepository<Worker, Long> {
}
