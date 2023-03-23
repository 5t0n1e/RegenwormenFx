package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RegenwormenModel {
    private List<Tegel> tegels;
    private List<Player> players;
    private Dice dice;
    private Roll currentRoll;
    private final int TEGEL_AMOUNT, DICE_AMOUNT;

    public RegenwormenModel(int tegelAmount, int diceAmount) {
        dice = new Dice();
        TEGEL_AMOUNT = tegelAmount;
        DICE_AMOUNT = diceAmount;
        tegels = initTegels();
    }

    public void rollDice() {
        currentRoll.resetRolls();
        for (int i = 0; i < currentRoll.getrollSize(); i++) {
            currentRoll.addRoll(dice.rollDice());
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        currentRoll = new Roll(players.get(0), DICE_AMOUNT);
    }

    public List<Tegel> initTegels() {
        List<Tegel> tegels = new LinkedList<>();
        int wurms = 1;
        for (int i = 1; i <= TEGEL_AMOUNT; i++) {
            Tegel tegel = new Tegel(wurms, i + 20);
            tegels.add(tegel);
            if (i % 4 == 0)
                wurms++;
        }
        return tegels;
    }

    public void updateRoll(int side) {
        currentRoll.sideUpdate(side);
    }

    public boolean checkChoice(int face) {
        return !(currentRoll.getSelected().containsKey(face));
    }

    public void finishRoll() {
        if (!(currentRoll.getSelected().containsKey(6))) {
            currentRoll.setKapot(true);
        }
        currentRoll.setFinished(true);
        getTegel();
    }

    public void getTegel() {
        // Normaal tegel pak systeem
        Tegel tegel = null;
        for (Tegel tegelIter : tegels) {
            if (tegelIter.getNumber() <= currentRoll.getTotalNumber()) {
                tegel = tegelIter;
            }
        }
        if (tegel != null) {
            currentRoll.getPlayer().addTegel(tegel);
            tegels.remove(tegel);
        } else {
            currentRoll.setKapot(true);
        }
    }

    public void kapot() {
        List<Tegel> playerTegels = currentRoll.getPlayer().getTegels();
        currentRoll.setKapot(true);
        if (playerTegels.size() > 0) {
            tegels.add(playerTegels.get(playerTegels.size() - 1));
            currentRoll.getPlayer().removeTegel(playerTegels.get(playerTegels.size() - 1).getNumber());
        }
        Collections.sort(tegels);
        if (tegels.size() > 0)
            tegels.remove(tegels.size() - 1);
    }

    public void createNextRoll() {
        Player nextPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(currentRoll.getPlayer())) {
                if (i == players.size() - 1) {
                    nextPlayer = players.get(0);
                } else {
                    nextPlayer = players.get(i + 1);
                }
            }
        }
        currentRoll = new Roll(nextPlayer, DICE_AMOUNT);
    }

    public boolean checkEnd() {
        if (tegels.size() == 0) {
            Collections.sort(players);
            return true;
        }
        return false;
    }

    public void checkKapotOrFinished() {
        currentRoll.checkKapot();
        currentRoll.checkFinished();
    }

    public boolean checkSteal(int stealNumber) {
        return stealNumber == currentRoll.getTotalNumber();
    }

    public void steal() {
        for (Player stealPlayer : players) {
            List<Tegel> playerTegels = stealPlayer.getTegels();
            if (playerTegels.size() > 0 && playerTegels.get(playerTegels.size() - 1).getNumber() == currentRoll.getTotalNumber()) {
                currentRoll.getPlayer().addTegel(playerTegels.get(playerTegels.size() - 1));
                stealPlayer.removeTegel(currentRoll.getTotalNumber());
            }
        }
    }

    public List<Tegel> getTegels() {
        return tegels;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Roll getCurrentRoll() {
        return currentRoll;
    }
}
