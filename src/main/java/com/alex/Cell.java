package com.alex;

public class Cell {

    private boolean road;
    private Entity entity;


    Cell(Entity entity, boolean road) {

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

}
