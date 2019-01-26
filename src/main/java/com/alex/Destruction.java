package com.alex;

public class Destruction extends Action {

    public Destruction(Position pos, Entity entity, Direction direct) {
        super(pos, entity, direct);
        actionName = "Destruction";
    }

    @Override
    public Position act() {
        int x = direction.getX(start.getX_dog());
        int y = direction.getY(start.getY_dog());

        Cell[][] area = copyArea(start.getArea());
        area[y][x].setEntity(Entity.EMPTY);
        return new Position(area, start.getX_cat(), start.getY_cat(), start.getX_dog(), start.getY_dog());
    }
}
