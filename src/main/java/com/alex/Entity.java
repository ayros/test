package com.alex;

public enum Entity {

    empty {
        public boolean isAvailable() {
            return true;
        }
    }, FULL_, ROCK_, _CAT_, _DOG_, ROAD_;

    public boolean isAvailable() {
        return false;
    }
}
