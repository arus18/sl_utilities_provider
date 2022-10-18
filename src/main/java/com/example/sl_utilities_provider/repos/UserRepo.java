package com.example.sl_utilities_provider.repos;

import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}