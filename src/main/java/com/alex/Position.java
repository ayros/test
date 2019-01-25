package com.alex;

import java.util.LinkedList;
import java.util.Queue;

public class Position {

    private Cell[][] area;
    private final int x_cat, x_dog, y_cat, y_dog;
    private Queue<Direction> cat_action = new LinkedList<Direction>();
    private Queue<Direction> dog_action = new LinkedList<Direction>();

    Position(Cell[][] area, int x_cat, int y_cat, int x_dog, int y_dog) {
        this.area = new Cell[Area.N][Area.M];
        for (int i = 0; i < Area.N; i++) {
            for (int j = 0; j < Area.M; j++) {
                this.area[i][j] = new Cell(area[i][j].getEntity(), j, i, area[i][j].isRoad());
            }
        }
        this.x_cat = x_cat;
        this.x_dog = x_dog;
        this.y_dog = y_dog;
        this.y_cat = y_cat;

        checkCat();
        checkDog();
    }

    private void checkCat() {
        for (Direction direction : Direction.values()) {
            if (area[direction.getY(y_cat)][direction.getX(x_cat)].getEntity().isAvailable()) {
                cat_action.add(direction);
            }
        }
    }

    private void checkDog() {
        for (Direction direction : Direction.values()) {
            if (area[direction.getY(y_dog)][direction.getX(x_dog)].getEntity().isAvailable()) {
                dog_action.add(direction);
                continue;
            }
            if (area[direction.getY(y_dog)][direction.getX(x_dog)].getEntity().equals(Entity.ROCK)) {
                dog_action.add(direction);
            }
        }
    }

    public boolean is_victory() {
        for (int i = 0; i < Area.N; i++) {
            for (int j = 0; j < Area.M; j++) {
                if (area[i][j].isRoad()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void info() {
        for (int i = 0; i < Area.N; i++) {
            for (int j = 0; j < Area.M; j++) {
                if (area[i][j].getEntity().toString().equals(" Cat ")) {
                    System.out.print(area[i][j].getEntity().toString() + " ");
                    continue;
                }
                if (area[i][j].isRoad()) {
                    System.out.print("road  ");
                    continue;
                }
                System.out.print(area[i][j].getEntity().toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void actionsInfo() {
        System.out.println();
        for (Direction direction : cat_action) {
            System.out.println(direction.toString());
        }
        for (Direction direction : dog_action) {
            System.out.println(direction.toString());
        }
    }

}


