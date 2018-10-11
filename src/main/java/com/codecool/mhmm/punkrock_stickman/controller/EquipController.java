package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.service.ItemHandler;
import com.codecool.mhmm.punkrock_stickman.service.JSONHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipController {

    private final Game game;
    private final ItemHandler itemHandler;
    private final JSONHandler jsonHandler;

    @Autowired
    public EquipController(Game game, ItemHandler itemHandler, JSONHandler jsonHandler) {
        this.game = game;
        this.itemHandler = itemHandler;
        this.jsonHandler = jsonHandler;
    }

    @GetMapping("/equip")
    public String equipItem(@RequestHeader(value = "equipWeapon", required = false) String weapon, @RequestHeader(value = "equipArmor", required = false) String armor) {
        String itemName = "";

        if (weapon != null) {
            itemHandler.equipWeapon(game.getPlayer(), weapon);
            itemName = weapon;
        } else if (armor != null) {
            itemHandler.equipArmor(game.getPlayer(), armor);
            itemName = armor;
        }
        return jsonHandler.gameStateToJson(game.getPlayer(), game.getLevel(), "Equipped: " + itemName);
    }
}
