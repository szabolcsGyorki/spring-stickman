package com.codecool.mhmm.punkrock_stickman.repository;

import com.codecool.mhmm.punkrock_stickman.model.map.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
