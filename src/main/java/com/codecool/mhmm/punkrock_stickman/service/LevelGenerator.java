package com.codecool.mhmm.punkrock_stickman.service;


import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.Wall;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.repository.EnemyRepository;
import com.codecool.mhmm.punkrock_stickman.repository.GameObjectRepository;
import com.codecool.mhmm.punkrock_stickman.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType.FLOOR;
import static com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType.WALL;

@Service
public class LevelGenerator {

    @Autowired
    private EnemyRepository enemyDAO;

    @Autowired
    private ItemRepository itemsDAO;
    @Autowired
    private GameObjectRepository gameObjectRepository;

    public Level levelOne() {
        Level levelOne = generateBase(10, 10, WALL, FLOOR);
        GameObjectType wallImage = levelOne.getWallImage();

        levelOne.setNumber(0);
        levelOne.addContent(new Wall(1, 2, wallImage));
        levelOne.addContent(new Wall(2, 2, wallImage));
        levelOne.addContent(new Wall(3, 2, wallImage));
        levelOne.addContent(new Wall(4, 2, wallImage));
        levelOne.addContent(new Wall(5, 2, wallImage));
        levelOne.addContent(new Wall(7, 2, wallImage));
        levelOne.addContent(new Wall(9, 2, wallImage));
        levelOne.addContent(new Wall(1, 5, wallImage));
        levelOne.addContent(new Wall(2, 5, wallImage));
        levelOne.addContent(new Wall(3, 5, wallImage));
        levelOne.addContent(new Wall(4, 5, wallImage));
        levelOne.addContent(new Wall(5, 5, wallImage));
        levelOne.addContent(new Wall(6, 5, wallImage));
        levelOne.addContent(new Wall(7, 4, wallImage));
        levelOne.addContent(new Wall(7, 3, wallImage));
        levelOne.addContent(new Wall(7, 5, wallImage));
        levelOne.addContent(new Wall(7, 6, wallImage));
        gameObjectRepository.saveAll(levelOne.getMap());

        levelOne.addContent(enemyDAO.findById(30L).get());
        levelOne.addContent(enemyDAO.findById(27L).get());
        levelOne.addContent(enemyDAO.findById(28L).get());
        levelOne.addContent(enemyDAO.findById(29L).get());

        levelOne.addContent(itemsDAO.findById(32L).get());
        levelOne.addContent(itemsDAO.findById(31L).get());

        return levelOne;
    }

    public Level levelTwo() {
        Level levelTwo = generateBase(10, 10, WALL, FLOOR);
        GameObjectType wallImage = levelTwo.getWallImage();

        levelTwo.setNumber(1);
        levelTwo.addContent(new Wall(2, 5, wallImage));
        levelTwo.addContent(new Wall(3, 5, wallImage));
        levelTwo.addContent(new Wall(4, 5, wallImage));
        levelTwo.addContent(new Wall(5, 5, wallImage));
        levelTwo.addContent(new Wall(6, 5, wallImage));
        levelTwo.addContent(new Wall(7, 4, wallImage));
        levelTwo.addContent(new Wall(7, 3, wallImage));
        levelTwo.addContent(new Wall(7, 5, wallImage));
        levelTwo.addContent(new Wall(7, 6, wallImage));
        gameObjectRepository.saveAll(levelTwo.getMap());

        levelTwo.addContent(enemyDAO.findById(30L).get());
        levelTwo.addContent(enemyDAO.findById(27L).get());
        levelTwo.addContent(enemyDAO.findById(28L).get());
        levelTwo.addContent(enemyDAO.findById(29L).get());

        levelTwo.addContent(itemsDAO.findById(31L).get());

        return levelTwo;
    }

    private Level generateBase(int width, int height, GameObjectType wall, GameObjectType floor) {
        Level level = new Level(width, height, wall, floor);

        for (int i = 0; i < level.getWIDTH(); i++) {
            level.addContent(new Wall(i, 0, level.getWallImage()));
            level.addContent(new Wall(i, level.getHEIGHT() - 1, level.getWallImage()));
        }
        for (int i = 1; i < level.getHEIGHT() - 1; i++) {
            level.addContent(new Wall(0, i, level.getWallImage()));
            level.addContent(new Wall(level.getWIDTH() - 1, i, level.getWallImage()));
        }

        return level;
    }

}
