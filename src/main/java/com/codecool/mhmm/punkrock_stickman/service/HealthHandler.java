package com.codecool.mhmm.punkrock_stickman.service;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Character;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.equipable.Armor;
import org.springframework.stereotype.Service;

@Service
public class HealthHandler {

    public void dealDamage(Character character, int damage) {
        int currentHealth = character.getHitPoint();
        int newHealth = currentHealth - damage;
        character.setHitPoint(newHealth);
    }

    public int calculateDragonHealth(int level) {
        return 100 + level * 20;
    }

    public int calculateOrcHealth(int level) {
        return 20 + 8 * (level - 1);
    }

    public int calculateSlimeHealth(int level) {
        return 12 * level / 2;
    }

    public int calculateSkeletonHealth(int level) {
        return 6 * level;
    }

    boolean characterIsDead(Character character) {
        return character.getHitPoint() <= 0;
    }

    private int getIncreasePlayerHealthByArmor(Player player, Armor newArmor) {
        int increaseHealth = newArmor.getHealthIncrease();
        Armor armor = player.getFullBody();
        increaseHealth -= (armor != null) ? armor.getHealthIncrease() : 0;
        return increaseHealth;
    }

    void increasePlayerHealthWithArmor(Player player, Armor armor) {
        int increaseHealth = getIncreasePlayerHealthByArmor(player, armor);
        player.setHitPoint(player.getHitPoint() + increaseHealth);
    }

    boolean armorChangeKillsPlayer(Player player, Armor armor) {
        int increaseHealth;
        increaseHealth = getIncreasePlayerHealthByArmor(player, armor);
        return player.getHitPoint() + increaseHealth <= 0;
    }

    boolean damageKillsCharacter(Character character, int damage) {
        return (character.getHitPoint() - damage) < 1;
    }
}
