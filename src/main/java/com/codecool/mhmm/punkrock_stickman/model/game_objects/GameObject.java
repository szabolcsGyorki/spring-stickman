package com.codecool.mhmm.punkrock_stickman.model.game_objects;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GameObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    protected GameObjectType type;

    @Column(name = "coordinate_x")
    protected int X;

    @Column(name = "coordinate_y")
    protected int Y;

    public GameObject(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    protected GameObject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameObjectType getType() {
        return this.type;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void place(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
