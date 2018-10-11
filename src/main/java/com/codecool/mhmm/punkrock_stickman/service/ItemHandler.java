package com.codecool.mhmm.punkrock_stickman.service;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Item;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Loot;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Armor;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Weapon;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.potion.HealthPotion;
import com.codecool.mhmm.punkrock_stickman.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Stream;

@Service
public class ItemHandler {

    private final ItemRepository itemsDAO;
    private final HealthHandler healthHandler;
    private Random random = new Random();

    @Autowired
    public ItemHandler(ItemRepository itemsDAO, HealthHandler healthHandler) {
        this.itemsDAO = itemsDAO;
        this.healthHandler = healthHandler;
    }

    void assignToPlayer(Player player, Item item) {
        player.addItemToInventory(item);
    }

    public void equipWeapon(Player player, String itemName) {
        Item item = itemsDAO.findByName(itemName);
        if (item instanceof Weapon)
            player.setWeapon((Weapon) item);
    }

    public void equipArmor(Player player, String itemName) {
        Item item = itemsDAO.findByName(itemName);
        if (item instanceof Armor) {
            if (!healthHandler.armorChangeKillsPlayer(player, (Armor) item)) {
                healthHandler.increasePlayerHealthWithArmor(player, (Armor) item);
                player.setFullBody((Armor) item);
            }
        }
    }

    public void setLootGold(Loot loot) {
        loot.setGold(random.nextInt(10) + 1);
    }

    public void fillUpLoot(Loot loot) {
        Stream.generate(itemsDAO::getRandomItem)
                .limit(random.nextInt(3) + 1)
                .forEach(loot::add);
    }

    public void pickUpLoot(Player player, Loot loot) {
        loot.getItems().forEach(player::addItemToInventory);
    }

    public void drinkPotion(Player player, String potion) {
        HealthPotion healthPotion = (HealthPotion) itemsDAO.findByName(potion);
        player.setHitPoint(player.getHitPoint() + healthPotion.getAmmount());
        player.removeItemFromInventory(healthPotion);
    }
}

