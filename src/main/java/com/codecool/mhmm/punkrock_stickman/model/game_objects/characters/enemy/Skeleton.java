package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;

import javax.persistence.Entity;

@Entity
public class Skeleton extends Enemy {
    public Skeleton(int X, int Y, int hitPoints, int level) {
        super(X, Y, hitPoints, (int) Math.round(level * (3.0 / 2.0)), level);
        this.dodgeChance = 25;
        this.type = GameObjectType.SKELETON;
    }

    public Skeleton() {
    }
}
