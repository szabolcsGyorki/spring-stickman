package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.service.ItemHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipController {

    private final Game game;
    private final ItemHandler itemHandler;

    @Autowired
    public EquipController(Game game, ItemHandler itemHandler) {
        this.game = game;
        this.itemHandler = itemHandler;
    }

    @GetMapping("/equip")
    public String equipItem(@RequestHeader("equipWeapon") String weapon, @RequestHeader("equipArmor") String armor) {
        String itemName = "";

        if (weapon != null) {
            itemHandler.equipWeapon(game.getPlayer(), weapon);
            itemName = weapon;
        } else if (armor != null) {
            itemHandler.equipArmor(game.getPlayer(), armor);
            itemName = armor;
        }
        return itemName + " equipped";
    }
}
