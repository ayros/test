package com.alex;

public abstract class Action {

    protected final Direction direction;
    protected final Position start;
    protected final Entity entity;
    protected int count;
    protected String actionName = "Action";

    public Action(Position pos, Entity entity, Direction direct, int count) {
        direction = direct;
        start = pos;
        this.entity = entity;
        this.count = count;
    }

    public abstract Position act();

    @Override
    public String toString() {
        return actionName + "{" +
                "direction=" + direction +
                ", entity=" + entity +
                '}';
    }

    static Cell[][] copyArea(Cell[][] pos) {
        Cell[][] area = new Cell[Area.N][Area.M];
        for (int i = 0; i < Area.N; i++) {
            for (int j = 0; j < Area.M; j++) {
                area[i][j] = new Cell(pos[i][j].getEntity(), pos[i][j].isRoad());
            }
        }
        return area;
    }
}
