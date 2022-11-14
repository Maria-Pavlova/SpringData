package com.example.softunigamestore.service;

import com.example.softunigamestore.models.dto.AllGamesDto;
import com.example.softunigamestore.models.dto.GameAddDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long gameId, BigDecimal price, Double size);

    void deleteGame(long parseLong);

    void allGames();

    void detailGame(String title);
}
