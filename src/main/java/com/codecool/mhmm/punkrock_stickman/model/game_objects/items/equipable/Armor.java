package com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Item;

import javax.persistence.Entity;

@Entity
public class Armor extends Item {

    private int healthIncrease;

    public Armor(String name, int value, int healthIncrease) {
        super(name, value);
        this.healthIncrease = healthIncrease;
        this.type = GameObjectType.ARMOR;
    }

    public Armor() {
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
