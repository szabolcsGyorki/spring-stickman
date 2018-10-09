package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Slime extends Enemy {

    private Random attackType;

    public Slime(int X, int Y, int hitPoints, int level, Random random) {
        super(X, Y, hitPoints, level, level);
        this.hitChance = 80;
        this.type = GameObjectType.SLIME;
        this.attackType = random;
    }

    public Slime() {
    }

    @Override
    public int getDamage() {
        int attackRoll = attackType.nextInt(100);
        if (attackRoll < 10) {
            return Math.round(level * 2);
        } else {
            return this.damage;
        }
    }
}
