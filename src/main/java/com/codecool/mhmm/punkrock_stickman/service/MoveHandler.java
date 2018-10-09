package com.codecool.mhmm.punkrock_stickman.service;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Character;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveHandler {

    public void moveLeft(Character character) {
        character.place(character.getX() - 1, character.getY());
    }

    public void moveRight(Character character) {
        character.place(character.getX() + 1, character.getY());
    }

    public void moveUp(Character character) {
        character.place(character.getX(), character.getY() - 1);
    }

    public void moveDown(Character character) {
        character.place(character.getX(), character.getY() + 1);
    }

    public GameObject getDestination(int toX, int toY, List<GameObject> map) {
        GameObject destination = null;
        for (GameObject mapElement : map) {
            if (mapElement.getX() == toX && mapElement.getY() == toY) {
                destination = mapElement;
                break;
            }
        }
        return destination;
    }
}
