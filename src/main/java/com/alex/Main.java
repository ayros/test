package com.alex;

public class Main {


    public static void main(String[] args) {

        Area area = new Area();

        long start = System.currentTimeMillis();
        game(area, 10);
        area.info();
        long end = (System.currentTimeMillis() - start);
        System.out.println("Время: " + end);
    }

    private static boolean game(Area pos, int fuel) {
        if (pos.getPosition().is_victory()) {
            System.out.println("Victory");
            return true;
        }
        if (fuel == 0) {
            return false;
        }
        while (pos.actionLeft()) {
            pos.step();
            if (game(pos, fuel - 1)) {
                return true;
            } else {
                pos.stepBack();
            }
        }
        return false;
    }
}

