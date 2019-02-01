package com.alex;

import java.util.Map;
import java.util.Set;

public class Movement extends Action {

    Movement(Position pos, Entity entity, Direction direct) {
        super(pos, entity, direct);
        actionName = "Movement";
    }

    @Override
    public Position act(int fuel) {
        Map<Entity, Set<Integer>> entities = copy(start.getEntities());
        int next, cell = 0;
        if (entity.equals(Entity._CAT_)) {
            cell = start.getCat();
        }
        if (entity.equals(Entity._DOG_)) {
            cell = start.getDog();
        }

        while (true) {
            next = direction.get(cell);
            if (!start.isEmpty(next)) {
                break;
            }
            if (entity.equals(Entity._CAT_) && entities.get(Entity.ROAD_).contains(cell)) {
                entities.get(Entity.ROAD_).remove(cell);
            }
            cell = next;
        }
        if (entity.equals(Entity._CAT_) && entities.get(Entity.ROAD_).contains(cell)) {
            entities.get(Entity.ROAD_).remove(cell);
        }
        entities.get(entity).clear();
        entities.get(entity).add(cell);

        return new Position(entities, fuel - 1);
    }


}
