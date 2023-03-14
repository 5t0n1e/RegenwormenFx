package Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
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

    public void addTegel(Tegel tegel) {
        tegels.add(tegel);
    }

    public void removeTegel(int number) {
        tegels.removeIf(tegel -> tegel.getNumber() == number);
    }

    public int getTotalWurms() {
        int worms = 0;
        for (Tegel tegel : tegels) {
            worms += tegel.getWurms();
        }
        return worms;
    }
}
