package com.codecool.mhmm.punkrock_stickman.config;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Dragon;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Orc;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Skeleton;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Slime;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Loot;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Armor;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Weapon;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.potion.HealthPotion;
import com.codecool.mhmm.punkrock_stickman.repository.EnemyRepository;
import com.codecool.mhmm.punkrock_stickman.repository.ItemRepository;
import com.codecool.mhmm.punkrock_stickman.repository.LevelRepository;
import com.codecool.mhmm.punkrock_stickman.service.HealthHandler;
import com.codecool.mhmm.punkrock_stickman.service.ItemHandler;
import com.codecool.mhmm.punkrock_stickman.service.LevelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InitDB {

    private boolean initialized = false;

    @Autowired
    private ItemRepository itemsDAO;
    @Autowired
    private LevelRepository levelDAO;
    @Autowired
    private EnemyRepository enemyDAO;
    @Autowired
    private LevelGenerator levelGenerator;
    @Autowired
    private HealthHandler healthHandler;
    @Autowired
    private ItemHandler itemHandler;

    public void init() {
        initPotions();
        initArmors();
        initWeapons();
        initEnemies();
        initLoots();
        initLevels();
        additionalInits();
        initialized = true;
    }

    private void initArmors() {
        itemsDAO.save(new Armor("Sacred Armor", 14030, 40));
        itemsDAO.save(new Armor("Shadow Plate", 100, 30));
        itemsDAO.save(new Armor("Kraken Shell", 10, 100));
        itemsDAO.save(new Armor("Diamond Mail", 80, 20));
        itemsDAO.save(new Armor("Shining Armor", 1300, 50));
        itemsDAO.save(new Armor("Flaming Armor", 1300, -10));
        itemsDAO.save(new Armor("Invisible Armor", 1300, 0));
    }

    private void initWeapons() {
        itemsDAO.save(new Weapon("RNG Sword", 1000, 1, 120));
        itemsDAO.save(new Weapon("Colossus Blade", 120, 12, 30));
        itemsDAO.save(new Weapon("Hydra Bow", 42, 1, 3));
        itemsDAO.save(new Weapon("Healing Wand", 100, -100, -10));
        itemsDAO.save(new Weapon("Flaming Flamingo", 5, 40, 41));
        itemsDAO.save(new Weapon("Ice Shard", 0, 0, 2));
        itemsDAO.save(new Weapon("Cthulhus book of Insanity", 600, -600, 600));
        itemsDAO.save(new Weapon("Forged Sword", 15, 22, 30));
        itemsDAO.save(new Weapon("Magma Mace", 3010, 17, 55));
        itemsDAO.save(new Weapon("Melted Mace", 10, 7, 15));
        itemsDAO.save(new Weapon("Melted ice", 0, 0, 0));
        itemsDAO.save(new Weapon("Mythical sword", 15, 20, 100));
        itemsDAO.save(new Weapon("Basic Bow", 10, 0, 3));
        itemsDAO.save(new Weapon("Basic Sword", 10, 3, 5));
        itemsDAO.save(new Weapon("Cruel Crude Candle", 14, 3, 8));
        itemsDAO.save(new Weapon("Phoenix Pike", 14, 33, 62));
        itemsDAO.save(new Weapon("Gandalfs lost and found staff", 2000, 1, 1));
        itemsDAO.save(new Weapon("Purging Pebble", 1, 39, 40));
    }

    private void initEnemies() {
        enemyDAO.save(new Slime(6, 2, healthHandler.calculateSlimeHealth(1), 1, new Random()));
        enemyDAO.save(new Skeleton(8, 2, healthHandler.calculateSkeletonHealth(1), 1));
        enemyDAO.save(new Orc(2, 4, healthHandler.calculateOrcHealth(1), 1));
        enemyDAO.save(new Dragon(6, 7, healthHandler.calculateDragonHealth(1), 1));
    }

    private void initLoots() {
        Loot loot1 = new Loot(4, 1);
        Loot loot2 = new Loot(1, 4);
        Loot loot3 = new Loot(3, 2);

        loot1.add(itemsDAO.findByName("Healing potion"));
        loot2.add(itemsDAO.findByName("Shadow Plate"));
        loot3.add(itemsDAO.findByName("Kraken Shell"));
        itemHandler.setLootGold(loot1);
        itemHandler.setLootGold(loot2);
        itemHandler.setLootGold(loot3);
        itemHandler.fillUpLoot(loot1);
        itemHandler.fillUpLoot(loot2);
        itemHandler.fillUpLoot(loot3);

        itemsDAO.save(loot1);
        itemsDAO.save(loot2);
        itemsDAO.save(loot3);
    }

    private void initLevels() {
        levelDAO.save(levelGenerator.levelOne());
        levelDAO.save(levelGenerator.levelTwo());
    }

    private void additionalInits() {
        enemyDAO.save(new Orc());
        itemsDAO.save(new HealthPotion("Super Healing potion", 30, 40));
        itemsDAO.save(new HealthPotion("Light Healing potion", 10, 10));
    }

    private void initPotions() {
        itemsDAO.save(new HealthPotion("Healing potion", 20, 20));
    }

    public boolean isInitialized() {
        return initialized;
    }
}
