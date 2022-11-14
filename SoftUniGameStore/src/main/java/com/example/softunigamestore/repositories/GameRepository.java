package com.example.softunigamestore.repositories;

import com.example.softunigamestore.models.dto.AllGamesDto;
import com.example.softunigamestore.models.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAll();


    Game findByTitle(String title);
}
