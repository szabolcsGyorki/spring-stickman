package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.config.InitDB;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Loot;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class StartGame {

    private final InitDB init;

    private final Game game;

    private final JSONHandler jsonHandler;

    private final MoveHandler moveHandler;

    @Autowired
    public StartGame(InitDB init, Game game, JSONHandler jsonHandler, MoveHandler moveHandler) {
        this.init = init;
        this.game = game;
        this.jsonHandler = jsonHandler;
        this.moveHandler = moveHandler;
    }

    @Autowired
    private FightHandler fightHandler;

    @Autowired
    private HealthHandler healthHandler;

    @Autowired
    private ItemHandler itemHandler;

    @GetMapping("/send")
    public String initGame() {
        if (!init.isInitialized()) {
            init.init();
        }

        String name = "";

        if (!game.isDemoLoaded()) {
            name = String.valueOf(UUID.randomUUID());
            game.initForDemo(name);
        }
        if (!game.isInitialized()) {
            game.initGame(name);
        }

        Sound.init();

        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Game loaded");
    }

    @GetMapping("/restart")
    public String restart(){
        game.initGame(game.getPlayer().getName());
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Restart");
    }

    @GetMapping("/move")
    public String move(@RequestHeader("move") String move) {

        Player player = game.getPlayer();
        Level level = game.getLevel();

        if (move.equals("down") && player.getY() < level.getHEIGHT() - 1) {
            moveHandler.moveDown(player);
        }
        if (move.equals("up") && player.getY() > 0) {
            moveHandler.moveUp(player);
        }
        if (move.equals("right") && player.getX() < level.getWIDTH() - 1) {
            moveHandler.moveRight(player);
        }
        if (move.equals("left") && player.getX() > 0) {
            moveHandler.moveLeft(player);
        }

        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Moved " + move);
    }



}
