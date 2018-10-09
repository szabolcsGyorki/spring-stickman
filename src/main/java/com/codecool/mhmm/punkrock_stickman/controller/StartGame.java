package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.service.JSONHandler;
import com.codecool.mhmm.punkrock_stickman.service.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartGame {

    @Autowired
    private Game game;

    @Autowired
    private JSONHandler jsonHandler;

    @GetMapping("/send")
    public String initGame() {
        String name = "";

        if (!game.isDemoLoaded()) {
            game.initForDemo();
            name = "Zsolt";
        }
        if (!game.isInitialized()) {
            game.initGame(name);
        }

        Sound.init();

        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Game loaded");
    }
}
