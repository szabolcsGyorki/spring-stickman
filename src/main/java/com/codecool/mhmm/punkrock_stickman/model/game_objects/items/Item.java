package com.codecool.mhmm.punkrock_stickman.model.game_objects.items;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Item.getAll", query = "SELECT i FROM Item i")
public abstract class Item extends GameObject {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(Item.class);
    private String name;
    private int value;

    protected Item(String name, int value) {
        super(0, 0);
        this.value = value;
        this.name = name;
    }

    protected Item() {
    }

    protected void assignToCharacter(Player player) {
        player.addItemToInventory(this);
        logger.info("'{}' added to inventory", this.name);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}