package com.alex.test.controller;

import java.util.Set;

public interface Info {

    public Set<Integer> getCoordinates(Entity entity);

    public boolean contains(Entity entity);
}
