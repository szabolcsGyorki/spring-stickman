package com.codecool.mhmm.punkrock_stickman.model.game_objects;

import javax.persistence.Entity;

@Entity
public class Wall extends GameObject {

    public Wall(int X, int Y, GameObjectType type) {
        super(X, Y);
        this.type = type;
    }

    public Wall() {
    }
}
