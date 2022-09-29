package com.example.sl_utilities_provider.repos;

import com.example.sl_utilities_provider.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}