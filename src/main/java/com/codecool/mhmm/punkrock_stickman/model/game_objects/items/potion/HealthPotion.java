package com.codecool.mhmm.punkrock_stickman.model.game_objects.items.potion;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Item;

import javax.persistence.Entity;

@Entity
public class HealthPotion extends Item {

    private int ammount;

    public HealthPotion(String name, int value, int ammount) {
        super(name, value);
        this.ammount = ammount;
        this.type = GameObjectType.HEALTH_POTION;
    }

    public HealthPotion() {
    }

    public int getAmmount() {
        return ammount;
    }
}
