package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Character extends GameObject {

    protected int level;
    protected int dodgeChance;
    protected int armor;
    protected int hitChance;
    private int hitPoint;

    public Character(int X, int Y, int hitPoint) {
        super(X, Y);
        this.hitPoint = hitPoint;
        this.dodgeChance = 0;
        this.armor = 0;
        this.hitChance = 100;
    }

    protected Character() {
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getHitChance() {
        return this.hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
}
