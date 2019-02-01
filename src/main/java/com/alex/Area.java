package com.alex;

import java.util.*;

class Area {

    final static int N = 10;
    final static int M = 13;

    private Deque<Position> positions = new LinkedList<Position>();
    private Set<Integer> bricks = new HashSet<Integer>();
    private Map<Position, Integer> positionIntegerMap = new HashMap<Position, Integer>();

    Area() {
        Cell[][] area = new Cell[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                area[i][j] = new Cell(Entity.empty, false);
            }
        }

        for (int i = 2; i < M; i++) {
            bricks.add(i);
        }

        for (int i = 8; i < M; i++) {
            bricks.add(5 * M + i);
        }

        for (int i = 0; i < 6; i++) {
            bricks.add(i * M + 12);
        }
        bricks.add(6 * M + 3);
        bricks.add(6 * M + 4);
        bricks.add(6 * M + 6);
        bricks.add(6 * M + 7);
        bricks.add(7 * M + 5);
        bricks.add(5 * M + 2);
        bricks.add(4 * M + 1);
        bricks.add(3 * M + 1);
        bricks.add(2 * M + 2);
        bricks.add(1 * M + 2);
        bricks.add(4 * M + 10);
        Position.setArea(bricks);

        Map<Entity, Set<Integer>> entities = new HashMap<Entity, Set<Integer>>();
        Set<Integer> rock = new HashSet<Integer>();
        rock.add(2 * M + 4);
        rock.add(3 * M + 7);
        rock.add(3 * M + 8);
        entities.put(Entity.ROCK_, rock);

        Set<Integer> roads = new HashSet<Integer>();
        for (int i = 2; i < 11; i++) {
            roads.add(3 * M + i);
        }

        roads.remove(3 * M + 7);
        roads.remove(3 * M + 8);

        roads.add(4 * M + 2);
        roads.add(1 * M + 7);
        roads.add(2 * M + 7);
        roads.add(4 * M + 7);
        roads.add(5 * M + 7);
        roads.add(1 * M + 4);
        roads.add(2 * M + 4);
        entities.put(Entity.ROAD_, roads);

        Set<Integer> cat = new HashSet<Integer>();
        cat.add(1 * M + 11);
        entities.put(Entity._CAT_, cat);

        Set<Integer> dog = new HashSet<Integer>();
        dog.add(5 * M + 3);
        entities.put(Entity._DOG_, dog);


        Position position = new Position(entities, 16);
        positionIntegerMap.put(position, 16);
        positions.addFirst(position);
    }

    boolean actionLeft() {
        Position position = positions.peekFirst();
        if (position.actionLeft()) {
            return true;
        }
        return false;
    }

    boolean step() {
        Position position = positions.peekFirst();
        Position next = position.nextPosition(getPosition().getFuel());
        if ((positionIntegerMap.get(next) == null) || (positionIntegerMap.get(next) < next.getFuel())) {
            positionIntegerMap.put(next, next.getFuel());
            positions.addFirst(next);
            return true;
        }
        positions.addFirst(next);
        return false;
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
            //pos.actionsInfo();
        }
    }

    void areaInfo() {
        Entity[][] entities = new Entity[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                entities[i][j] = Entity.empty;
            }
        }
        int err = 0;
        try {
            for (Integer i : bricks) {
                err = i;
                entities[i / M][i % M] = Entity.FULL_;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(err);
        }
        for (Integer i : bricks) {
            entities[i / M][i % M] = Entity.FULL_;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(entities[i][j]);
            }
            System.out.println();
        }
    }

    public Set<Integer> getBricks() {
        return bricks;
    }

    public void positionsInfo() {
        for (Position pos : positionIntegerMap.keySet()) {
            pos.info();
            System.out.println();
        }
    }
}

