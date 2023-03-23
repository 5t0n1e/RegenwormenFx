package Model;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {
    private final String name;
    private List<Tegel> tegels;

    public Player(String name) {
        this.name = name;
        tegels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Tegel> getTegels() {
        return tegels;
    }

    /**
     * Voegt een tegel toe.
     */
    public void addTegel(Tegel tegel) {
        tegels.add(tegel);
    }

    /**
     * Verwijdert een tegel.
     */
    public void removeTegel(int number) {
        tegels.removeIf(tegel -> tegel.getNumber() == number);
    }

    /**
     * Geeft het totaal aantal wormen weer.
     */
    public int getTotalWurms() {
        int worms = 0;
        for (Tegel tegel : tegels) {
            worms += tegel.getWurms();
        }
        return worms;
    }

    /**
     * Vergelijkt het totaal aantal wormen van de speler met die van het spel.
     * @param o het object dat vergeleken moet worden.
     */
    @Override
    public int compareTo(Player o) {
        return o.getTotalWurms() - getTotalWurms();
    }
}
