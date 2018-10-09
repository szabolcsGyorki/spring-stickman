package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;

import javax.persistence.Entity;

@Entity
public class Dragon extends Enemy {

    private int fireBreathTimer;

    public Dragon(int X, int Y, int hitPoints, int level) {
        super(X, Y, hitPoints, 15 + level * 5, level);
        this.armor = 20 + level * 3;
        dodgeChance = 15;
        hitChance = 90;
        fireBreathTimer = 2;
        this.type = GameObjectType.DRAGON;
    }

    public Dragon() {
    }

    @Override
    public int getDamage() {
        if (fireBreathTimer < 1) {
            fireBreathTimer = 3;
            return level * 5 + this.damage;
        }
        fireBreathTimer -= 1;
        return this.damage;
    }
}

