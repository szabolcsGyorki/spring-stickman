package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;

import javax.persistence.Entity;

@Entity
public class Orc extends Enemy {

    public Orc(int X, int Y, int hitPoints, int level) {
        super(X, Y, hitPoints, 5 + 2 * (level - 1), level);
        this.armor = (int) (2 + Math.round((1.0 / 2.0) * (level - 1)));
        this.type = GameObjectType.ORC;
    }

    public Orc() {
    }
}