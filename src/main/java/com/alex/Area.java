package com.alex;

import java.util.Deque;
import java.util.LinkedList;

public class Area {

    public final static int N = 10;
    public final static int M = 12;

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

        for (int i = 1; i < M - 1; i++) {
            area[5][i].setRoad(true);
        }

        area[1][1].setEntity(Entity._CAT_);
        area[N - 2][M - 2].setEntity(Entity._DOG_);

        Position position = new Position(area, 1, 1, M - 2, N - 2);
        positions.addFirst(position);
    }

    public void step() {

    }

    public Position getPosition() {
        return positions.peekFirst();
    }

    public void info() {
        for (Position pos : positions) {
            pos.info();
            pos.actionsInfo();
        }
    }
}

