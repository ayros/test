package com.alex.test.controller;

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
        for (int i : map.get(Entity.ROCK)) {
            copyRock.add(i);
        }
        copy.put(Entity.ROCK, copyRock);

        Set<Integer> copyRoad = new HashSet<Integer>();
        for (int i : map.get(Entity.ROAD)) {
            copyRoad.add(i);
        }
        copy.put(Entity.ROAD, copyRoad);

        Set<Integer> copyCat = new HashSet<Integer>();
        for (int i : map.get(Entity.CAT)) {
            copyCat.add(i);
        }
        copy.put(Entity.CAT, copyCat);

        Set<Integer> copyDog = new HashSet<Integer>();
        for (int i : map.get(Entity.DOG)) {
            copyDog.add(i);
        }
        copy.put(Entity.DOG, copyDog);

        Set<Integer> copyICE = new HashSet<Integer>();
        for (int i : map.get(Entity.ICE)) {
            copyICE.add(i);
        }
        copy.put(Entity.ICE, copyICE);

        Set<Integer> copyWater = new HashSet<Integer>();
        for (int i : map.get(Entity.WATER)) {
            copyWater.add(i);
        }
        copy.put(Entity.WATER, copyWater);
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
