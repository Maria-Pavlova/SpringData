package com.example.softunigamestore.service.impl;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.entity.Game;
import com.example.softunigamestore.models.entity.User;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.service.GameService;
import com.example.softunigamestore.service.UserService;
import com.example.softunigamestore.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {

        if(!userService.hasLoggedInUser()){
            return;
        }
        User loggedInUser = userService.getLoggedInUser();

        if (!loggedInUser.isAdmin()){
            throw new UnsupportedOperationException("Unsupported operation. User must be admin");
        }

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);
        System.out.println("Added game" + gameAddDto.getTitle());
    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        Game game = gameRepository.findById(gameId).orElse(null);

        if (game== null){
            System.out.println("Id is not valid");
            return;
        }
        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public void deleteGame(long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            System.out.println("The game is not present");
            return;
        }
            gameRepository.delete(game);
            System.out.println("Deleted " + game.getTitle());

    }

    @Override
    public void allGames() {
      //  List<Game> games =
                gameRepository.findAll()
                        .forEach(g-> System.out.printf("%s %.2f%n",g.getTitle(), g.getPrice()));

    }

    @Override
    public void detailGame(String title) {
        Game game = gameRepository.findByTitle(title);
        System.out.println(game);
    }
}
