package com.codecool.mhmm.punkrock_stickman.repository;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObject, Long> {
}
