package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Character;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Enemy extends Character {

    int damage;

    Enemy(int X, int Y, int hitPoints, int damage, int level) {
        super(X, Y, hitPoints);
        this.level = level;
        this.damage = damage;
    }

    protected Enemy() {
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}