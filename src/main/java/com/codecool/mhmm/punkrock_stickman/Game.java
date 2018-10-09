package com.codecool.mhmm.punkrock_stickman;

import com.codecool.mhmm.punkrock_stickman.config.InitDB;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import com.codecool.mhmm.punkrock_stickman.repository.EnemyRepository;
import com.codecool.mhmm.punkrock_stickman.repository.ItemRepository;
import com.codecool.mhmm.punkrock_stickman.repository.LevelRepository;
import com.codecool.mhmm.punkrock_stickman.repository.PlayerRepository;
import com.codecool.mhmm.punkrock_stickman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class Game {

    @Autowired
    private ItemRepository itemsDAO;
    @Autowired
    private EnemyRepository enemyDao;
    @Autowired
    private LevelRepository levelDao;
    @Autowired
    private PlayerRepository playerDAO;
    @Autowired
    private HealthHandler healthHandler;
    @Autowired
    private LevelGenerator levelGenerator;
    @Autowired
    private MoveHandler moveHandler;
    @Autowired
    private ItemHandler itemHandler;
    @Autowired
    private FightHandler fightHandler;
    @Autowired
    private InitDB init;

    private boolean initialized = false;
    private boolean demoLoaded = false;

    //GUEST TRIAL STUFF
    private Player player;
    private Level level;

    public void initForDemo() {
        init.init();
        playerDAO.save(new Player(1, 1, "Zsolt"));
        demoLoaded = true;
    }

    public void initGame(String name) {
        player = playerDAO.findByName(name);
        level = (Level) levelDao.findAll().get(0);
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
        level = (Level) levelDao.findAll().get(next);
        player.place(1, 1);
        level.addContent(player);
    }
}
