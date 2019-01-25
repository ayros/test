package com.alex;

public class Cell {

    private boolean road;
    private Entity entity;
    private int x;
    private int y;

    Cell(Entity entity, int x, int y, boolean road) {
        this.x = x;
        this.y = y;
        this.road = road;
        this.entity = entity;
    }

    public boolean isRoad() {
        return road;
    }

    public void setRoad(boolean road) {
        this.road = road;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
