package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RegenwormenModel {
    private List<Tegel> tegels;
    private List<Player> players;
    private Dice dice;
    private Roll currentRoll;
    private final int TEGEL_AMOUNT;

    public RegenwormenModel() {
        dice = new Dice();
        TEGEL_AMOUNT = 17;
        tegels = initTegels();
    }

    /**
     * Zorgt voor een worp tijdens het spel
     */
    public void rollDice() {
        currentRoll.resetRolls();
        for (int i = 0; i < currentRoll.getrollSize(); i++) {
            currentRoll.addRoll(dice.rollDice());
        }
    }

    /**
     * Initiëren van de spelers in het spel
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
        currentRoll = new Roll(players.get(0));
    }
    /**
     * Initiëren van de tegels in het spel
     */
    public List<Tegel> initTegels() {
        List<Tegel> tegels = new LinkedList<>();
        int wurms = 1;
        for (int i = 1; i < TEGEL_AMOUNT; i++) {
            if (i % 4 == 0)
                wurms++;
            Tegel tegel = new Tegel(wurms, i + 20);
            tegels.add(tegel);
        }
        return tegels;
    }
    public void updateRoll(int side) {
        currentRoll.sideUpdate(side);
    }

    /**
     * Voegt keuze van de worp toe aan geselecteerde dobbelstenen
     * @param face waarde van de dobbelsteen
     */
    public boolean checkChoice(int face) {
        return !(currentRoll.getSelected().containsKey(face));
    }
    public void finishRoll() {
        if (!(currentRoll.getSelected().containsKey(6))) {
            currentRoll.setKapot(true);
        }
        //int number = 0;
        //for (Map.Entry<Integer, Integer> select : currentRoll.getSelected().entrySet()) {
        //    number += select.getKey() * select.getValue();
        //}
        //currentRoll.setTotalNumber(number);
        currentRoll.setFinished(true);
        getTegel();
    }

    /**
     * Neemt tegel uit het spel en geeft deze aan de speler
     */
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

    /**
     * Kijkt of de speler kapot is gespeeld
     */
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

    /**
     * Maakt een nieuwe worp aan
     */
    public void createNextRoll() {
        Player nextPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(currentRoll.getPlayer())) {
                if (i == players.size() - 1) {
                    nextPlayer = players.get(0);
                } else {
                    nextPlayer = players.get(i+1);
                }
            }
        }
        currentRoll = new Roll(nextPlayer);
    }

    /**
     * Kijkt of het spel afgelopen is
     * @return true: er zijn geen tegels meer over in het spel, false: er zijn nog tegels aanwezig in het spel
     */
    public boolean checkEnd() {
        if (tegels.size() == 0) {
            Collections.sort(players);
            return true;
        }
        return false;
    }

    /**
     * Kijkt of de worp kapot of gedaan is
     */
    public void checkKapotOrFinished(){
        currentRoll.checkKapot();
        currentRoll.checkFinished();
    }

    /**
     * Kijkt na of er een tegel gesteeld kan worden van een andere speler
     * @param stealNumber steel tegel met waarde
     * @return true: er kan gesteeld worden, false: er kan niet gesteeld worden
     */
    public boolean checkSteal(int stealNumber) {
        if (stealNumber == currentRoll.getTotalNumber()) {
            return true;
        }
        return false;
    }

    /**
     * Maakt het mogelijk om een tegel van een andere speler te stelen
     */
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
    public Dice getDice() {
        return dice;
    }
    public Roll getCurrentRoll() {
        return currentRoll;
    }
}
