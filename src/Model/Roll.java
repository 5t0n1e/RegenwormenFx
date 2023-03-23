package Model;

import java.util.*;

public class Roll {
    private int rollSize;
    private List<Integer> rolls;
    private Map<Integer, Integer> selected;
    private int totalNumber;
    private Player player;
    private boolean finished;
    private boolean kapot;

    public Roll(Player currentPlayer, int rollSize) {
        this.rolls = new LinkedList<>();
        player = currentPlayer;
        selected = new HashMap<>();
        kapot = false;
        this.rollSize = rollSize;
    }

    public void addRoll(int roll) {
        rolls.add(roll);
    }

    public void sideUpdate(int side) {
        selected.put(side, Collections.frequency(rolls, side));
        //rolls.removeIf(integer -> integer == side);
        for (Iterator<Integer> rollIter = rolls.iterator(); rollIter.hasNext(); ) {
            if (rollIter.next() == side) {
                rollIter.remove();
                totalNumber += side;
                rollSize--;
            }
        }
    }

    public void checkFinished() {
        if (rolls.size() == 0) {
            finished = true;
        }
    }

    public void checkKapot() {
        int count = 0;
        for (Integer checkRoll : rolls) {
            if (selected.containsKey(checkRoll)) {
                count++;
            }
        }
        if (!finished)
            kapot = count == rolls.size();
    }

    public void resetRolls() {
        rolls = new ArrayList<>();
    }

    public void setKapot(boolean kapot) {
        this.kapot = kapot;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }


    public Map<Integer, Integer> getSelected() {
        return selected;
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public boolean isKapot() {
        return kapot;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getrollSize() {
        return rollSize;
    }

    public Player getPlayer() {
        return player;
    }
}
