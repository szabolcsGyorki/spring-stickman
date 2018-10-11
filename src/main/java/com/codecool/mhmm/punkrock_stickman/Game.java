package com.codecool.mhmm.punkrock_stickman;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.repository.EnemyRepository;
import com.codecool.mhmm.punkrock_stickman.repository.ItemRepository;
import com.codecool.mhmm.punkrock_stickman.repository.LevelRepository;
import com.codecool.mhmm.punkrock_stickman.repository.PlayerRepository;
import com.codecool.mhmm.punkrock_stickman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Game {

    private final LevelRepository levelDao;
    private final PlayerRepository playerDAO;

    private boolean initialized = false;
    private boolean demoLoaded = false;

    //GUEST TRIAL STUFF
    private Player player;
    private Level level;

    @Autowired
    public Game(LevelRepository levelDao, PlayerRepository playerDAO) {
        this.levelDao = levelDao;
        this.playerDAO = playerDAO;
    }

    public void initForDemo(String name) {
        playerDAO.save(new Player(1, 1, name));
        demoLoaded = true;
    }

    public void initGame(String name) {
        player = playerDAO.findByName(name);
        level = levelDao.findAll().get(0);
        level.addContent(player);
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isDemoLoaded() {
        return demoLoaded;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public Player getPlayer(HttpSession session) {
        return (Player) session.getAttribute("Player");
    }

    public void setPlayer(HttpSession session, Player player) {
        session.setAttribute("Player", player);
    }

    public Level getLevel(HttpSession session) {
        return (Level) session.getAttribute("Level");
    }

    public void setLevel(HttpSession session, Level level) {
        session.setAttribute("Level", level);
    }

    public void nextLevel() {
        int next = level.getNumber() + 1;
        level = levelDao.findAll().get(next);
        player.place(1, 1);
        level.addContent(player);
    }

}
