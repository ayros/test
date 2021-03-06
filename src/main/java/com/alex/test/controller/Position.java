package com.alex.test.controller;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Position implements Info {

    private static Set<Integer> area;
    private Map<Entity, Set<Integer>> entities;
    private Queue<Action> cat_action = new LinkedList<Action>();
    private Queue<Action> dog_action = new LinkedList<Action>();
    private int fuel = 10;

    Position(Map<Entity, Set<Integer>> entities, int fuel) {
        this.entities = entities;
        this.fuel = fuel;
        checkCat();
        checkDog();
    }

    private void checkCat() {
        int cell;
        for (Direction direction : Direction.values()) {
            cell = direction.get(getCat());
            if (isEmpty(cell)) {
                cat_action.add(new Movement(this, Entity.CAT, direction));
            }
        }
    }

    private void checkDog() {
        int cell;
        for (Direction direction : Direction.values()) {
            cell = direction.get(getDog());
            if (entities.get(Entity.ROCK).contains(cell)) {
                dog_action.add(new Destruction(this, Entity.DOG, direction));
                continue;
            }
            if (isEmpty(cell)) {
                dog_action.add(new Movement(this, Entity.DOG, direction));
            }
        }
    }

    boolean is_victory() {
        if (entities.get(Entity.ROAD).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    void info() {
        Cell[][] area = new Cell[Direction.N][Direction.M];
        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                area[i][j] = new Cell(Entity.EMPTY, false);
            }
        }
        for (Integer i : this.area) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.FULL);
        }
        for (Integer i : entities.get(Entity.ROCK)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.ROCK);
        }
        for (Integer i : entities.get(Entity.ROAD)) {
            area[i / Direction.M][i % Direction.M].setRoad(true);
        }
        for (Integer i : entities.get(Entity.CAT)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.CAT);
        }
        for (Integer i : entities.get(Entity.DOG)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.DOG);
        }

        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                if (area[i][j].isRoad() && area[i][j].getEntity().equals(Entity.EMPTY)) {
                    System.out.print("road  ");
                    continue;
                }
                System.out.print(area[i][j].getEntity().toString() + " ");
            }
            System.out.println();
        }

        System.out.println("fuel = " + fuel);
    }

    boolean actionLeft() {
        if (cat_action.isEmpty() && dog_action.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isEmpty(int cell) {
        if (area.contains(cell) || entities.get(Entity.ROCK).contains(cell) || entities.get(Entity.WATER).contains(cell)
                || (cell == getCat()) || (cell == getDog())) {
            return false;
        }
        return true;
    }

    Position nextPosition(int fuel) {
        Action action;
        if (!cat_action.isEmpty()) {
            action = cat_action.remove();
        } else {
            action = dog_action.remove();
        }
        return action.act(fuel);
    }

    void actionsInfo() {
        System.out.println();
        for (Action direction : cat_action) {
            System.out.println(direction.toString());
        }
        for (Action direction : dog_action) {
            System.out.println(direction.toString());
        }
    }

    int getCat() {
        for (int cat : entities.get(Entity.CAT)) {
            return cat;
        }
        return 0;
    }

    int getDog() {
        for (int dog : entities.get(Entity.DOG)) {
            return dog;
        }
        return 0;
    }

    Map<Entity, Set<Integer>> getEntities() {
        return entities;
    }


    public static void setArea(Set<Integer> area) {
        Position.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return entities.equals(position.entities);
    }

    @Override
    public int hashCode() {
        return entities.hashCode();
    }

    public int getFuel() {
        return fuel;
    }

    @Override
    public Set<Integer> getCoordinates(Entity entity) {
        return entities.get(entity);
    }

    @Override
    public boolean contains(Entity entity) {
        return entities.containsKey(entity);
    }

    @Override
    public String toString() {
        return "fuel=" + fuel;
    }
}


