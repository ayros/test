package com.alex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Action {

    protected final Direction direction;
    protected final Position start;
    protected final Entity entity;
    protected String actionName = "Action";

    public Action(Position pos, Entity entity, Direction direct) {
        direction = direct;
        start = pos;
        this.entity = entity;
    }

    public abstract Position act(int fuel);

    public static Map<Entity, Set<Integer>> copy(Map<Entity, Set<Integer>> map) {
        Map<Entity, Set<Integer>> copy = new HashMap<Entity, Set<Integer>>();
        Set<Integer> copyRock = new HashSet<Integer>();
        for (int i : map.get(Entity.ROCK_)) {
            copyRock.add(i);
        }
        copy.put(Entity.ROCK_, copyRock);

        Set<Integer> copyRoad = new HashSet<Integer>();
        for (int i : map.get(Entity.ROAD_)) {
            copyRoad.add(i);
        }
        copy.put(Entity.ROAD_, copyRoad);

        Set<Integer> copyCat = new HashSet<Integer>();
        for (int i : map.get(Entity._CAT_)) {
            copyCat.add(i);
        }
        copy.put(Entity._CAT_, copyCat);

        Set<Integer> copyDog = new HashSet<Integer>();
        for (int i : map.get(Entity._DOG_)) {
            copyDog.add(i);
        }
        copy.put(Entity._DOG_, copyDog);

        return copy;
    }
    @Override
    public String toString() {
        return actionName + "{" +
                "direction=" + direction +
                ", entity=" + entity +
                '}';
    }

}
