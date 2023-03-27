package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RegenwormenModel {
    private List<Tegel> tegels;
    private List<Player> players;
    private Dice dice;
    private Roll currentRoll;
    private final int TEGEL_AMOUNT, DICE_AMOUNT;

    /**
     * Constructor voor de klasse RegenwormenModel.
     *
     * @param tegelAmount Het aantal tegels dat beschikbaar is in het spel.
     * @param diceAmount Het aantal dobbelstenen dat gebruikt wordt in het spel.
     */
    public RegenwormenModel(int tegelAmount, int diceAmount) {
        dice = new Dice();
        TEGEL_AMOUNT = tegelAmount;
        DICE_AMOUNT = diceAmount;
        tegels = initTegels();
    }

    /**
     * Methode om de dobbelstenen te rollen.
     * Reset de huidige rol en rolt dan alle niet geselecteerde dobbelstenen.
     */
    public void rollDice() {
        currentRoll.resetRolls();
        for (int i = 0; i < currentRoll.getrollSize(); i++) {
            currentRoll.addRoll(dice.rollDice());
        }
    }

    /**
     * Methode om de spelers in te stellen.
     * @param players Een lijst van alle spelers die aan het spel meedoen.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
        Random gen = new Random();
        currentRoll = new Roll(players.get(gen.nextInt(0, players.size())), DICE_AMOUNT);
    }

    /**
     * Initialiseert alle tegels in het spel en returned deze.
     * @return Lijst van alle tegels in het spel.
     */
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

    /**
     * Update de huidige worp met het gekozen aantal ogen.
     * @param side Het aantal ogen dat geselecteerd is.
     */
    public void updateRoll(int side) {
        currentRoll.sideUpdate(side);
    }

    /**
     * Controleert of het gekozen aantal ogen niet al geselecteerd is.
     * @param face Het aantal ogen
     */
    public boolean checkChoice(int face) {
        return !(currentRoll.getSelected().containsKey(face));
    }

    /**
     * Beëindig de huidige worp en, checkt nog eens of deze kapot is.
     */
    public void finishRoll() {
        if (!(currentRoll.getSelected().containsKey(6))) {
            currentRoll.setKapot(true);
        } else {
            getTegel();
        }
        currentRoll.setFinished(true);
    }

    /**
     * Selecteert een tegel op basis van het totale aantal ogen dat werd gegooid en voegt deze toe aan de speler.
     * Als er geen tegel gevonden wordt, wordt de huidige worp als "kapot" gemarkeerd.
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
     * Verwijdert tegel van de speler en/of uit het spel wanneer deze speler kapot gaat in zijn worp.
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
     * Maakt een nieuwe worp aan.
     */
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

    /**
     * Controleert of het spel beëindigd is.
     * @return of het spel is beëindigd.
     */
    public boolean checkEnd() {
        if (tegels.size() == 0) {
            Collections.sort(players);
            return true;
        }
        return false;
    }

    /**
     * Controleert of de huidige worp leiden tot een "kapotte" of "afgeronde" worp.
     */
    public void checkKapotOrFinished() {
        currentRoll.checkKapot();
        currentRoll.checkFinished();
    }

    /**
     * Controleert of het gegeven getal overeenkomt met het totale getal van de huidige rol.
     * @param stealNumber het getal dat moet worden vergeleken met het totale getal van de huidige rol.
     * @return  of het gegeven getal overeenkomt met het totale getal van de huidige rol.
     */
    public boolean checkSteal(int stealNumber) {
        return stealNumber == currentRoll.getTotalNumber();
    }

    /**
     * Steelt een tegel van de speler die een steen heeft met hetzelfde getal als het totaal van de huidige worp.
     */
    public void steal() {
        for (Player stealPlayer : players) {
            List<Tegel> playerTegels = stealPlayer.getTegels();
            if (playerTegels.size() > 0 && playerTegels.get(playerTegels.size() - 1).getNumber() == currentRoll.getTotalNumber()) {
                currentRoll.getPlayer().addTegel(playerTegels.get(playerTegels.size() - 1));
                stealPlayer.removeTegel(currentRoll.getTotalNumber());
                return;
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
