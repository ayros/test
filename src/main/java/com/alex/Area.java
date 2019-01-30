package com.alex;

import java.util.Deque;
import java.util.LinkedList;

class Area {

    final static int N = 10;
    final static int M = 13;

    private Deque<Position> positions = new LinkedList<Position>();

    Area() {
        Cell[][] area = new Cell[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                area[i][j] = new Cell(Entity.empty, false);
            }
        }

        for (int i = 0; i < M; i++) {
            area[0][i].setEntity(Entity.FULL_);
        }
        for (int i = 0; i < 6; i++) {
            area[i][12].setEntity(Entity.FULL_);
        }
        for (int i = 8; i < M; i++) {
            area[5][i].setEntity(Entity.FULL_);
        }


        for (int i = 2; i < 6; i++) {
            area[i][7].setRoad(true);
        }

        area[6][3].setEntity(Entity.FULL_);
        area[6][4].setEntity(Entity.FULL_);
        area[6][6].setEntity(Entity.FULL_);
        area[6][7].setEntity(Entity.FULL_);

        area[7][5].setEntity(Entity.FULL_);
        area[5][2].setEntity(Entity.FULL_);
        area[4][1].setEntity(Entity.FULL_);
        area[3][1].setEntity(Entity.FULL_);
        area[2][2].setEntity(Entity.FULL_);
        area[1][2].setEntity(Entity.FULL_);

        area[2][4].setEntity(Entity.ROCK);
        area[3][7].setEntity(Entity.ROCK);
        area[3][8].setEntity(Entity.ROCK);
        area[4][10].setEntity(Entity.FULL_);

        for (int i = 2; i < 11; i++) {
            area[3][i].setRoad(true);
        }
        area[3][7].setRoad(false);
        area[3][8].setRoad(false);

        area[4][2].setRoad(true);
        area[1][7].setRoad(true);
        area[2][7].setRoad(true);
        area[4][7].setRoad(true);
        area[5][7].setRoad(true);
        area[1][4].setRoad(true);
        area[2][4].setRoad(true);

        int x_cat, y_cat, x_dog, y_dog;
        x_cat = 11;
        y_cat = 1;
        x_dog = 3;
        y_dog = 5;

        area[y_dog][x_dog].setEntity(Entity._DOG_);
        area[y_cat][x_cat].setEntity(Entity._CAT_);

        Position position = new Position(area, x_cat, y_cat, x_dog, y_dog, 13);
        positions.addFirst(position);
    }

    boolean actionLeft() {
        Position position = positions.peekFirst();
        if (position.actionLeft()) {
            return true;
        }
        return false;
    }

    void step() {
        Position position = positions.peekFirst();
        positions.addFirst(position.nextPosition());
    }

    void stepBack() {
        positions.removeFirst();
    }

    Position getPosition() {
        return positions.peekFirst();
    }

    void info() {
        for (Position pos : positions) {
            pos.info();
            pos.actionsInfo();
        }
    }
}

