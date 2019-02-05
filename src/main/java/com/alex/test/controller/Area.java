package com.alex.test.controller;

import com.alex.test.view.OutPut;

import java.util.*;

public class Area extends Thread implements OutputSubject {

    final static int N = 11;
    final static int M = 15;

    private Set<OutPut> outPuts = new LinkedHashSet<OutPut>();
    private Deque<Position> positions = new LinkedList<Position>();
    private Set<Integer> bricks = new HashSet<Integer>();
    private Map<Position, Integer> positionIntegerMap = new HashMap<Position, Integer>();

    public Area(com.alex.test.view.Cell[][] area, int fuel) {
        super("search");
        Set<Integer> rock = new HashSet<Integer>();
        Set<Integer> roads = new HashSet<Integer>();
        Set<Integer> cat = new HashSet<Integer>();
        Set<Integer> dog = new HashSet<Integer>();
        Set<Integer> ice = new HashSet<Integer>();
        Set<Integer> water = new HashSet<Integer>();
        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                for (Entity entity : area[i][j].getEntities()) {
                    switch (entity) {
                        case FULL:
                            bricks.add(i * Direction.M + j);
                            break;
                        case ROAD:
                            roads.add(i * Direction.M + j);
                            break;
                        case ROCK:
                            rock.add(i * Direction.M + j);
                            break;
                        case CAT:
                            cat.add(i * Direction.M + j);
                            break;
                        case DOG:
                            dog.add(i * Direction.M + j);
                            break;
                        case ICE:
                            ice.add(i * Direction.M + j);
                    }
                }
            }
        }
        Position.setArea(bricks);
        Map<Entity, Set<Integer>> entities = new HashMap<Entity, Set<Integer>>();
        entities.put(Entity.ROCK, rock);
        entities.put(Entity.ROAD, roads);
        entities.put(Entity.CAT, cat);
        entities.put(Entity.DOG, dog);
        entities.put(Entity.ICE, ice);
        entities.put(Entity.WATER, water);

        Position position = new Position(entities, fuel);
        positionIntegerMap.put(position, fuel);
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

    public void info() {
        for (Position pos : positions) {
            pos.info();
        }
    }

    void areaInfo() {
        Entity[][] entities = new Entity[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                entities[i][j] = Entity.EMPTY;
            }
        }
        int err = 0;
        try {
            for (Integer i : bricks) {
                err = i;
                entities[i / M][i % M] = Entity.FULL;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(err);
        }
        for (Integer i : bricks) {
            entities[i / M][i % M] = Entity.FULL;
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

    public void run() {
        if (search()) {
            System.out.println("Victory");
        }
        info();
        result();
    }

    private boolean search() {
        if (getPosition().is_victory()) {
            return true;
        }
        if (getPosition().getFuel() == 0) {
            return false;
        }
        while (actionLeft()) {
            if (step()) {
                if (search()) {
                    return true;
                } else {
                    stepBack();
                }
            } else {
                stepBack();
            }
        }
        return false;
    }

    @Override
    public void addOutput(OutPut outPut) {
        outPuts.add(outPut);
    }

    @Override
    public void removeOutput(OutPut outPut) {
        outPuts.remove(outPut);
    }

    @Override
    public void result() {
        for (OutPut outPut : outPuts) {
            outPut.output(new LinkedList<Info>(positions), new LinkedHashSet<Integer>(bricks));
        }
    }
}

