package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.Game;
import com.codecool.mhmm.punkrock_stickman.service.ItemHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotionController {


    private ItemHandler itemHandler;
    private Game game;

    @Autowired
    public PotionController(ItemHandler itemHandler, Game game) {
        this.itemHandler = itemHandler;
        this.game = game;
    }

    @GetMapping("/potion")
    public String drinkPotion(@RequestHeader("potion") String potion) {
        itemHandler.drinkPotion(game.getPlayer(), potion);
        return "Player drank " + potion;
    }

}
