package com.codecool.mhmm.punkrock_stickman.service;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Character;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Loot;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Weapon;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FightHandler {

    private Random random = new Random();

    int getPlayerDamage(Player player) {
        int baseDamage = player.getStrength();
        Weapon weapon = player.getWeapon();
        if (weapon != null) {
            int minDmg = weapon.getMinDamage();
            int maxDmg = weapon.getMaxDamage();
            if (minDmg == maxDmg) {
                baseDamage += minDmg;
            } else {
                baseDamage += random.nextInt(maxDmg - minDmg) + minDmg;
            }
        }
        return baseDamage;
    }

    boolean characterHits(Character character) {
        return random.nextInt(100) < character.getHitChance();
    }

    boolean characterDodges(Character character) {
        return random.nextInt(100) < character.getDodgeChance();
    }

    public String playerHitsEnemy(Player player, Enemy enemy, HealthHandler healthHandler) {
        GameObjectType enemyType = enemy.getType();
        String response = "";

        if (characterHits(player)) {
            int playerDamage = getPlayerDamage(player);
            if (!characterDodges(enemy)) {
                healthHandler.dealDamage(enemy, playerDamage);
                response += "Your attack hits " + enemyType + " for " + playerDamage + " damage. ";
                Sound.playAttack(GameObjectType.MAIN_CHARACTER);
            } else {
                Sound.playMiss();
                response += enemyType + " dodges your attack. ";
            }
        } else {
            response += "Your attack misses. ";
        }
        return response;
    }

    public String enemyHitsPlayer(Player player, Enemy enemy, HealthHandler healthHandler) {
        GameObjectType enemyType = enemy.getType();
        String response = "";

        if (characterHits(enemy)) {
            int enemyDamage = enemy.getDamage();
            if (!characterDodges(player)) {
                healthHandler.dealDamage(player, enemyDamage);
                response += enemyType + "'s attack hits your for " + enemyDamage + " damage.";
                Sound.playAttack(enemyType);
            } else {
                Sound.playMiss();
                response += "You dodge " + enemyType + "'s attack.";
            }
        } else {
            response += enemyType + "'s attack misses.";
        }
        return response;
    }

    public String enemyDies(Level level, ItemHandler itemHandler, List<GameObject> map, Enemy enemy) {
        Sound.playDie(enemy.getType());
        map.remove(enemy);
        Loot loot = new Loot(enemy.getX(), enemy.getY());
        itemHandler.setLootGold(loot);
        itemHandler.fillUpLoot(loot);
        level.addContent(loot);
        return enemy.getType() + " dies.";
    }
}
