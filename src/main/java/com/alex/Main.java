package com.alex;

public class Main {


    public static void main(String[] args) {

        Area area = new Area();

        long start = System.currentTimeMillis();
        game(area);
        long end = (System.currentTimeMillis() - start);
        //area.positionsInfo();
        area.info();
        System.out.println("Время: " + end + "мс");
    }

    private static boolean game(Area pos) {
        if (pos.getPosition().is_victory()) {
            System.out.println("Victory");
            return true;
        }
        if (pos.getPosition().getFuel() == 0) {
            return false;
        }
        while (pos.actionLeft()) {
            if (pos.step()) {
                if (game(pos)) {
                    return true;
                } else {
                    pos.stepBack();
                }
            } else {
                pos.stepBack();
            }
        }
        return false;
    }
}

