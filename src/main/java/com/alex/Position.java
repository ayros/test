package com.alex;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Position {

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
                cat_action.add(new Movement(this, Entity._CAT_, direction));
            }
        }
    }

    private void checkDog() {
        int cell;
        for (Direction direction : Direction.values()) {
            cell = direction.get(getDog());
            if (entities.get(Entity.ROCK_).contains(cell)) {
                dog_action.add(new Destruction(this, Entity._DOG_, direction));
                continue;
            }
            if (isEmpty(cell)) {
                dog_action.add(new Movement(this, Entity._DOG_, direction));
            }
        }
    }

    boolean is_victory() {
        if (entities.get(Entity.ROAD_).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    void info() {
        Cell[][] area = new Cell[Direction.N][Direction.M];
        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                area[i][j] = new Cell(Entity.empty, false);
            }
        }
        for (Integer i : this.area) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.FULL_);
        }
        for (Integer i : entities.get(Entity.ROCK_)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity.ROCK_);
        }
        for (Integer i : entities.get(Entity.ROAD_)) {
            area[i / Direction.M][i % Direction.M].setRoad(true);
        }
        for (Integer i : entities.get(Entity._CAT_)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity._CAT_);
        }
        for (Integer i : entities.get(Entity._DOG_)) {
            area[i / Direction.M][i % Direction.M].setEntity(Entity._DOG_);
        }

        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                if (area[i][j].isRoad() && area[i][j].getEntity().equals(Entity.empty)) {
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
        if (area.contains(cell) || entities.get(Entity.ROCK_).contains(cell) || (cell == getCat()) || (cell == getDog())) {
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
        for (int cat : entities.get(Entity._CAT_)) {
            return cat;
        }
        return 0;
    }

    int getDog() {
        for (int dog : entities.get(Entity._DOG_)) {
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
}


