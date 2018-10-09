package com.codecool.mhmm.punkrock_stickman.repository;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByName(String name);
}
