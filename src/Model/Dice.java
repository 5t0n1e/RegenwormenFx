package Model;

import java.util.Random;

public class Dice {

    /**
     * Genereert 8 random gekozen dobbelstenen
     * @return dice
     */
    public int rollDice() {
        Random gen = new Random();
        return gen.nextInt(1, 7);
    }
}
