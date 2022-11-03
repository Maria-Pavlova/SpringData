package com.example.usersystem.repositories;

import com.example.usersystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailEndingWith(String provider);

    List<User> findAllByLastTimeLoggedInBefore(LocalDateTime localDateTime);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.lastTimeLoggedIn < :localDateTime")
    int updateUsersIsDeleted(LocalDateTime localDateTime);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.isDeleted = true")
    int deleteAllByDeletedIsTrue();

}
