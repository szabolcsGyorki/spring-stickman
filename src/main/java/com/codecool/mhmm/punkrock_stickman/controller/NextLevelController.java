package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.service.JSONHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NextLevelController {

    private final Game game;
    private final JSONHandler jsonHandler;

    @Autowired
    public NextLevelController(Game game, JSONHandler jsonHandler) {
        this.game = game;
        this.jsonHandler = jsonHandler;
    }

    @GetMapping("/next-level")
    public String loadNextLevel() {
        game.nextLevel();
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Level loaded");
    }

}
