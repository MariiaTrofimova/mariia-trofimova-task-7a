package model;

import java.util.Random;

public class Roulette {
    private int number;

    public int getNumber() {
        return number;
    }

    public int start() {
        Random random = new Random();
        number = random.nextInt(36);
        return number;
    }
}
