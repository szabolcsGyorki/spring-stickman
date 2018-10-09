package com.codecool.mhmm.punkrock_stickman.repository;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.characters.enemy.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
}
