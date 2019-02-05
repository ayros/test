package com.alex.test.controller;

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
        if (entity.equals(Entity.CAT)) {
            cell = start.getCat();
        }
        if (entity.equals(Entity.DOG)) {
            cell = start.getDog();
        }
        while (true) {
            next = direction.get(cell);
            if (!start.isEmpty(next)) {
                break;
            }
            if (entities.get(Entity.ICE).contains(cell)) {
                entities.get(Entity.ICE).remove(cell);
                entities.get(Entity.WATER).add(cell);
            }
            if (entity.equals(Entity.CAT) && entities.get(Entity.ROAD).contains(cell)) {
                entities.get(Entity.ROAD).remove(cell);
            }
            cell = next;
        }
        if (entity.equals(Entity.CAT) && entities.get(Entity.ROAD).contains(cell)) {
            entities.get(Entity.ROAD).remove(cell);
        }
        entities.get(entity).clear();
        entities.get(entity).add(cell);

        return new Position(entities, fuel - 1);
    }


}
