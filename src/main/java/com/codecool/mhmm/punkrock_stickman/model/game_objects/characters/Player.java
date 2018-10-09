package com.codecool.mhmm.punkrock_stickman.model.game_objects.characters;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Item;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Armor;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Weapon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player extends Character {

    private String name;
    private int strength;
    private int agility;
    private int intelligence;

    @ManyToOne
    private Armor fullBody;

    @ManyToOne
    private Weapon weapon;

    @ManyToMany
    @JoinTable(name = "player_inventory")
    private List<Item> items = new ArrayList<>();

    public Player(int X, int Y, String name) {
        super(X, Y, 300);
        this.type = GameObjectType.MAIN_CHARACTER;
        strength = 30;
        agility = 3;
        intelligence = 3;
        this.name = name;
    }

    public Player() {
    }

    public void changeStrength(int changeAmount) {
        this.strength += changeAmount;
    }

    boolean strengthTest(int testValue) {
        return testValue < strength;
    }

    void changeAgility(int changeAmount) {
        this.agility += changeAmount;
    }

    boolean agilityTest(int testValue) {
        return testValue < agility;
    }

    void changeIntelligence(int changeAmount) {
        this.intelligence += changeAmount;
    }

    boolean intelligenceTest(int testValue) {
        return testValue < intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }

    public Armor getFullBody() {
        return fullBody;
    }

    public void setFullBody(Armor fullBody) {
        this.fullBody = fullBody;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public void removeItemFromInventory(Item item) {
        items.remove(item);
    }

    public Item getItemById(long id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Item> getItems() {
        return items;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayDamage() {
        if (this.weapon == null)
            return String.valueOf(strength);
        return String.valueOf(this.getWeapon().getMinDamage() + this.strength)
                + " - "
                + String.valueOf(this.getWeapon().getMaxDamage() + this.strength);
    }
}

