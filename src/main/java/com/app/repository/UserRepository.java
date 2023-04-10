package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);
    User findByUserId(int userId);
    Optional<User> findByUsername(String username);
}
