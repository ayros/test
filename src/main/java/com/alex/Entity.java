package com.alex;

public enum Entity {

    empty {
        public boolean isAvailable() {
            return true;
        }
    }, FULL_, ROCK, _CAT_, _DOG_;

    public boolean isAvailable() {
        return false;
    }
}
