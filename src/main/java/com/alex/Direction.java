package com.alex;

public enum Direction {
    UP {
        int getY(int y) {
            return y - 1;
        }
    },
    DOWN {
        int getY(int y) {
            return y + 1;
        }
    },
    LEFT {
        int getX(int x) {
            return x - 1;
        }
    },
    RIGHT {
        int getX(int x) {
            return x + 1;
        }
    };

    int getX(int x) {
        return x;
    }

    int getY(int y) {
        return y;
    }
}
