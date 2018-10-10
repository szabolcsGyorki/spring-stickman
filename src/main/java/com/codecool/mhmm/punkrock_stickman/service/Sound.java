package com.codecool.mhmm.punkrock_stickman.service;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Sound {

    private static Map<GameObjectType, Runnable> attackSounds = new HashMap();
    private static Map<GameObjectType, Runnable> deathSounds = new HashMap();

    public static void init() {
        attackSounds.put(GameObjectType.SKELETON, Sound::playSkeletonAttack);
        attackSounds.put(GameObjectType.MAIN_CHARACTER, Sound::playSwordSwing);
        attackSounds.put(GameObjectType.SLIME, Sound::playSlimeAttack);
        attackSounds.put(GameObjectType.DRAGON, Sound::playDragonAttack);
        attackSounds.put(GameObjectType.ORC, Sound::playOrcAttack);

        deathSounds.put(GameObjectType.SKELETON, Sound::playSkeletonDeath);
        deathSounds.put(GameObjectType.MAIN_CHARACTER, Sound::playPlayerDies);
        deathSounds.put(GameObjectType.SLIME, Sound::playSlimeDeath);
        deathSounds.put(GameObjectType.DRAGON, Sound::playDragonDeath);
        deathSounds.put(GameObjectType.ORC, Sound::playOrcDeath);
    }

    static void playAttack(GameObjectType type) {
        attackSounds.get(type).run();
    }

    public static void playDie(GameObjectType type) {
        deathSounds.get(type).run();
    }

    private static void playSound(String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create an audiostream from the inputstream
        AudioStream audioStream = null;
        try {
            assert in != null;
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }

    private static void playSwordSwing() {
        playSound("src/main/resources/static/sounds/sword_swipe.wav");
    }

    private static void playPlayerDies() {
        playSound("src/main/resources/static/sounds/player_die.wav");
    }

    private static void playSkeletonAttack() {
        playSound("src/main/resources/static/sounds/skeleton_attack.wav");
    }

    private static void playSkeletonDeath() {
        playSound("src/main/resources/static/sounds/skeleton_die.wav");
    }

    private static void playSlimeAttack() {
        playSound("src/main/resources/static/sounds/slime_attack.wav");
    }

    private static void playSlimeDeath() {
        playSound("src/main/resources/static/sounds/slime_die.wav");
    }

    private static void playOrcAttack() {
        playSound("src/main/resources/static/sounds/orc_attack.wav");
    }

    private static void playOrcDeath() {
        playSound("src/main/resources/static/sounds/orc_die.wav");
    }

    private static void playDragonAttack() {
        playSound("src/main/resources/static/sounds/dragon_attack.wav");
    }

    private static void playDragonDeath() {
        playSound("src/main/resources/static/sounds/dragon_die.wav");
    }

    static void playMiss() {
        playSound("src/main/resources/static/sounds/missed.wav");
    }

    public static void playGameOver() {
        playSound("src/main/resources/static/sounds/game_over.wav");
    }

    public static void playLoot() {
        playSound("src/main/resources/static/sounds/loot.wav");
    }

    public static void playWon() {
        playSound("src/main/resources/static/sounds/game_won.wav");
    }
}
