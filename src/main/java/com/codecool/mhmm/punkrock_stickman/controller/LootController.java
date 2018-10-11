package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Loot;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.service.ItemHandler;
import com.codecool.mhmm.punkrock_stickman.service.JSONHandler;
import com.codecool.mhmm.punkrock_stickman.service.MoveHandler;
import com.codecool.mhmm.punkrock_stickman.service.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LootController {

    private Game game;
    private MoveHandler moveHandler;
    private ItemHandler itemHandler;
    private JSONHandler jsonHandler;

    @Autowired
    LootController(Game game, MoveHandler moveHandler, ItemHandler itemHandler, JSONHandler jsonHandler) {
        this.game = game;
        this.moveHandler = moveHandler;
        this.itemHandler = itemHandler;
        this.jsonHandler = jsonHandler;
    }

    @GetMapping("/loot")
    public String loot(@RequestHeader("pickUpLoot") String actionRequired) {
        Player player = game.getPlayer();
        Level level = game.getLevel();

        if (actionRequired != null) {
            Loot loot;
            List<GameObject> map = level.getMap();
            switch (actionRequired) {
                case "down":
                    loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() + 1, map);
                    break;
                case "up":
                    loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() - 1, map);
                    break;
                case "right":
                    loot = (Loot) moveHandler.getDestination(player.getX() + 1, player.getY(), map);
                    break;
                default:
                    loot = (Loot) moveHandler.getDestination(player.getX() - 1, player.getY(), map);
                    break;
            }
            itemHandler.pickUpLoot(player, loot);
            Sound.playLoot();
            map.remove(loot);
        }
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "You picked up new loot.");
    }
}
