package com.alex;

import java.util.LinkedList;
import java.util.Queue;

class Position {

    private Cell[][] area;
    private final int x_cat, x_dog, y_cat, y_dog;
    private int count = 10;
    private Queue<Action> cat_action = new LinkedList<Action>();
    private Queue<Action> dog_action = new LinkedList<Action>();

    Position(Cell[][] area, int x_cat, int y_cat, int x_dog, int y_dog, int count) {
        this.area = area;
        this.x_cat = x_cat;
        this.x_dog = x_dog;
        this.y_dog = y_dog;
        this.y_cat = y_cat;
        this.count = count;
        //System.out.println(count);

        checkCat();
        checkDog();
        //System.out.println(count);
    }

    private void checkCat() {
        for (Direction direction : Direction.values()) {
            if (area[direction.getY(y_cat)][direction.getX(x_cat)].getEntity().isAvailable()) {
                //System.out.println(count);
                cat_action.add(new Movement(this, Entity._CAT_, direction, count));
            }
        }
    }

    private void checkDog() {
        for (Direction direction : Direction.values()) {
            if (area[direction.getY(y_dog)][direction.getX(x_dog)].getEntity().isAvailable()) {
                //System.out.println(count);
                dog_action.add(new Movement(this, Entity._DOG_, direction, count));
                continue;
            }
            if (area[direction.getY(y_dog)][direction.getX(x_dog)].getEntity().equals(Entity.ROCK)) {
                //System.out.println(count);
                dog_action.add(new Destruction(this, Entity._DOG_, direction, count));
            }
        }
    }

    boolean is_victory() {
        if (count <= 0) {
            return true;
        } else {
            return false;
        }
    }

    void info() {
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

    boolean actionLeft() {
        if (cat_action.isEmpty() && dog_action.isEmpty()) {
            return false;
        }
        return true;
    }

    Position nextPosition() {
        Action action;
        if (!cat_action.isEmpty()) {
            action = cat_action.remove();
        } else {
            action = dog_action.remove();
        }
        return action.act();
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

    int getX_cat() {
        return x_cat;
    }

    int getX_dog() {
        return x_dog;
    }

    int getY_cat() {
        return y_cat;
    }

    int getY_dog() {
        return y_dog;
    }

    Cell[][] getArea() {
        return area;
    }
}


