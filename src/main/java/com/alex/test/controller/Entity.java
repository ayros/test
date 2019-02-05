package com.alex.test.controller;

public enum Entity {

    EMPTY {
        public boolean isAvailable() {
            return true;
        }
    }, FULL, ROCK, CAT, DOG, ROAD, ICE, WATER;

    public boolean isAvailable() {
        return false;
    }
}
