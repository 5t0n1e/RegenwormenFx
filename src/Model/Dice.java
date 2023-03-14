package Model;

import java.util.Random;

public class Dice {

    public int rollDice() {
        Random gen = new Random();
        return gen.nextInt(1, 7);
    }
}
