package com.alex;

import java.util.Deque;
import java.util.LinkedList;

class Area {

    final static int N = 10;
    final static int M = 12;

    private Deque<Position> positions = new LinkedList<Position>();

    Area() {
        Cell[][] area = new Cell[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                area[i][j] = new Cell(Entity.EMPTY, false);
            }
        }

        for (int i = 0; i < M; i++) {
            area[0][i].setEntity(Entity.FULL_);
            area[N - 1][i].setEntity(Entity.FULL_);
        }

        for (int i = 0; i < N; i++) {
            area[i][0].setEntity(Entity.FULL_);
            area[i][M - 1].setEntity(Entity.FULL_);
        }

        for (int i = 2; i < M - 1; i++) {
            area[N - 3][i].setRoad(true);
        }
        int x_cat, y_cat, x_dog, y_dog;
        x_cat = 1;
        y_cat = 1;
        x_dog = M - 2;
        y_dog = 1;
        area[y_cat][x_cat].setEntity(Entity._CAT_);
        area[y_dog][x_dog].setEntity(Entity._DOG_);

        Position position = new Position(area, x_cat, y_cat, x_dog, y_dog);
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

