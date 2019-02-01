package com.alex;

import java.util.Map;
import java.util.Set;

public class Destruction extends Action {

    public Destruction(Position pos, Entity entity, Direction direct) {
        super(pos, entity, direct);
        actionName = "Destruction";
    }

    @Override
    public Position act(int fuel) {
        Map<Entity, Set<Integer>> entities = copy(start.getEntities());
        int x = direction.get(start.getDog());

        entities.get(Entity.ROCK_).remove(x);
        return new Position(entities, fuel);
    }
}
