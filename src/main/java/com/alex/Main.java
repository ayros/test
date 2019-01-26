package com.alex;

public class Main {


    public static void main(String[] args) {

        Area area = new Area();


        game(area, 5);
        area.info();
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

