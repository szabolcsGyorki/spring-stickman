package com.codecool.mhmm.punkrock_stickman.repository;

import com.codecool.mhmm.punkrock_stickman.model.game_objects.GameObjectType;
import com.codecool.mhmm.punkrock_stickman.model.game_objects.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);
    List<Item> findByType(GameObjectType type);

    default Item getRandomItem() {

        List<Item> items = new ArrayList<>(findByType(GameObjectType.WEAPON));
        items.addAll(findByType(GameObjectType.ARMOR));
        items.addAll(findByType(GameObjectType.HEALTHPOTION));

        int id = new Random().nextInt(items.size());
        return items.get(id);
    }
}
