package com.example.workshopnextleveltechnologiesmvc.repositories;

import com.example.workshopnextleveltechnologiesmvc.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

}
