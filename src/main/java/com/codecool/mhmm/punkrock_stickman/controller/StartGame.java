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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
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

        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Moved to the " + move);
    }

    @GetMapping("/fight")
    public String FightController(@RequestHeader("fight") String fight){

        List<GameObject> map = game.getLevel().getMap();
        Enemy enemy = getEnemy(fight);

        Player player = game.getPlayer();
        Level level = game.getLevel();

        String response = "";
        assert enemy != null;

        response += fightHandler.playerHitsEnemy(player, enemy, healthHandler);

        if (enemy.getHitPoint() > 0) {
            response += fightHandler.enemyHitsPlayer(player, enemy, healthHandler);
        } else {
            response += fightHandler.enemyDies(level, itemHandler, map, enemy);
        }

        if (player.getHitPoint() <= 0) {
            Sound.playDie(GameObjectType.MAIN_CHARACTER);
            Sound.playGameOver();
        }
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), response);
    }

    private Enemy getEnemy(String actionRequired) {
        Enemy enemy = null;
        if (actionRequired != null) {
            if (actionRequired.equals("down")) {
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX(), game.getPlayer().getY() + 1, game.getLevel().getMap());
            } else if (actionRequired.equals("up")) {
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX(), game.getPlayer().getY() - 1, game.getLevel().getMap());
            } else if (actionRequired.equals("right")) {
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX() + 1, game.getPlayer().getY(), game.getLevel().getMap());
            } else {
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX() - 1,game.getPlayer().getY(), game.getLevel().getMap());
            }
        }
        return enemy;
    }

    @GetMapping("/loot")
    public String LootController(@RequestHeader("pickUpLoot") String actionRequired){
        Player player = game.getPlayer();
        Level level = game.getLevel();

        if (actionRequired != null) {
            Loot loot;
            List<GameObject> map = level.getMap();
            if (actionRequired.equals("down")) {
                loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() + 1, map);
            } else if (actionRequired.equals("up")) {
                loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() - 1, map);
            } else if (actionRequired.equals("right")) {
                loot = (Loot) moveHandler.getDestination(player.getX() + 1, player.getY(), map);
            } else {
                loot = (Loot) moveHandler.getDestination(player.getX() - 1, player.getY(), map);
            }
            itemHandler.pickUpLoot(player, loot);
            Sound.playLoot();
            map.remove(loot);
        }
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(),"You picked up new loot.");
    }

    @GetMapping("/won")
    public String WinController(){
        Sound.playWon();
        return null;
    }


}
