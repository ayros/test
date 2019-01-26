package com.alex;

public abstract class Action {

    protected final Direction direction;
    protected final Position start;
    protected final Entity entity;

    protected String actionName = "Action";

    public Action(Position pos, Entity entity, Direction direct) {
        direction = direct;
        start = pos;
        this.entity = entity;
    }

    public abstract Position act();

    @Override
    public String toString() {
        return actionName + "{" +
                "direction=" + direction +
                ", entity=" + entity +
                '}';
    }
}
