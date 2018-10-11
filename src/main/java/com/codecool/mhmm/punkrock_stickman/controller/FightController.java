package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FightController {
    private Game game;
    private FightHandler fightHandler;
    private HealthHandler healthHandler;
    private ItemHandler itemHandler;
    private JSONHandler jsonHandler;
    private MoveHandler moveHandler;

    @Autowired
    public FightController(Game game, FightHandler fightHandler, HealthHandler healthHandler, ItemHandler itemHandler, JSONHandler jsonHandler, MoveHandler moveHandler) {
        this.game = game;
        this.fightHandler = fightHandler;
        this.healthHandler = healthHandler;
        this.itemHandler = itemHandler;
        this.jsonHandler = jsonHandler;
        this.moveHandler = moveHandler;
    }

    @GetMapping("/fight")
    public String fight(@RequestHeader("fight") String fight) {

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
        switch (actionRequired) {
            case "down":
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX(), game.getPlayer().getY() + 1, game.getLevel().getMap());
                break;
            case "up":
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX(), game.getPlayer().getY() - 1, game.getLevel().getMap());
                break;
            case "right":
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX() + 1, game.getPlayer().getY(), game.getLevel().getMap());
                break;
            case "left":
                enemy = (Enemy) moveHandler.getDestination(game.getPlayer().getX() - 1, game.getPlayer().getY(), game.getLevel().getMap());
                break;
        }
        return enemy;
    }
}
