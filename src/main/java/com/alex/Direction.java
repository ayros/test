package com.alex;

public enum Direction {

    UP {
        int get(int cell) {
            return cell - M;
        }
    },
    DOWN {
        int get(int cell) {
            return cell + M;
        }
    },
    LEFT {
        int get(int cell) {
            return cell - 1;
        }
    },
    RIGHT {
        int get(int cell) {
            return cell + 1;
        }
    };

    public static final int M = 13;
    public static final int N = 10;


    abstract int get(int cell);
}
