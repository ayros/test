package com.alex;

public class Movement extends Action {

    Movement(Position pos, Entity entity, Direction direct, int count) {
        super(pos, entity, direct, count);
        actionName = "Movement";
        //System.out.println(this.count + toString());
    }

    @Override
    public Position act() {
        int x = 0, y = 0;
        Cell[][] area = copyArea(start.getArea());
        if (entity.equals(Entity._CAT_)) {
            x = start.getX_cat();
            y = start.getY_cat();
        }
        if (entity.equals(Entity._DOG_)) {
            x = start.getX_dog();
            y = start.getY_dog();
        }

        while (area[direction.getY(y)][direction.getX(x)].getEntity().isAvailable()) {
            if (area[y][x].isRoad() && entity.equals(Entity._CAT_)) {
                area[y][x].setRoad(false);
                count--;
            }
            area[y][x].setEntity(Entity.empty);
            y = direction.getY(y);
            x = direction.getX(x);
            area[y][x].setEntity(entity);
        }
        if (entity.equals(Entity._CAT_)) {
            area[y][x].setRoad(false);
        }

        if (entity.equals(Entity._CAT_)) {
            return new Position(area, x, y, start.getX_dog(), start.getY_dog(), count);
        } else {
            return new Position(area, start.getX_cat(), start.getY_cat(), x, y, count);
        }

    }


}
