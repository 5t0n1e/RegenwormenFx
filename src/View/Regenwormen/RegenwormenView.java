package View.Regenwormen;

import Model.Player;
import Model.Roll;
import Model.Tegel;
import View.DiceImage;
import View.TegelImage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.*;

public class RegenwormenView extends BorderPane {
    private List<TegelImage> tegels, players;
    private DiceImage[] dices;
    private List<Label> names;
    private GridPane topPane;
    private BorderPane bottemPane;
    private Button throwDice, stopRoll;
    private Label currentPlayer;
    private HBox thrownDices, chosenDices;
    private VBox centerPanes;

    public RegenwormenView() {
        setBackground(new Background(new BackgroundImage(new Image("resources/Home.png"), null, null, null, null)));
        topPane = new GridPane();
        bottemPane = new BorderPane();
        bottemPane.setBackground(new Background(new BackgroundImage(new Image("resources/Spelbord.png"), null, null, null, null)));
        topPane.setAlignment(Pos.CENTER);
        bottemPane.setMaxWidth(820);
        bottemPane.setMaxHeight(273);
        bottemPane.setMinHeight(273);
        bottemPane.setMinWidth(820);
        centerPanes = new VBox(topPane, bottemPane);
        centerPanes.setAlignment(Pos.CENTER);
        setCenter(centerPanes);

        throwDice = new Button("");
        throwDice.setBackground(new Background(new BackgroundImage(new Image("resources/Werp_dobbelstenen.jpg"), null, null, null, null)));
        throwDice.setMinSize(259,194);
        throwDice.setAlignment(Pos.CENTER);

        stopRoll = new Button("");
        stopRoll.setBackground(new Background(new BackgroundImage(new Image("resources/Stop_Worp_1.png"), null, null, null, null)));
        stopRoll.setMinSize(259,194);
        stopRoll.setAlignment(Pos.BASELINE_RIGHT);

        dices = new DiceImage[8];
        thrownDices = new HBox();
        chosenDices = new HBox();
        thrownDices.setAlignment(Pos.TOP_RIGHT);
        chosenDices.setAlignment(Pos.BOTTOM_LEFT);
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new DiceImage();
        }
    }

    /**
     * Initialiseren van de attributen
     */
    public void initialiseNodes(List<Player> players, List<Tegel> tegels) {
        updateTegels(tegels);
        showPlayers(players);
        setBottom(new HBox(throwDice, stopRoll));
        currentPlayer = new Label(String.format("%s is aan de beurt!!", players.get(0).getName()));
        topPane.add(currentPlayer, 0, 0);
        topPane.setVgap(20);
        topPane.setHgap(20);
    }

    /**
     * Geeft alle spelers weer
     */
    private void showPlayers(List<Player> players) {
        this.players = new ArrayList<>();
        this.names = new ArrayList<>();
        VBox playersRight = new VBox();
        VBox playersLeft = new VBox();
        int split = players.size() / 2;
        for (int i = 0; i < players.size(); i++) {
            this.players.add(new TegelImage("resources/player.png"));
            this.players.get(i).setFitWidth(150);
            this.players.get(i).setFitHeight(300);
            names.add(new Label(players.get(i).getName()));
            if (i <= split - 1) {
                playersLeft.getChildren().addAll(this.players.get(i), names.get(i));
            } else {
                playersRight.getChildren().addAll(this.players.get(i), names.get(i));
            }
        }
        setRight(playersRight);
        setLeft(playersLeft);
    }

    /**
     * Vernieuwd de gegooide en gekozen dobbelstenen op het spelbord
     * @param roll Worp
     */
    public void updateDices(Roll roll) {
        thrownDices.getChildren().clear();
        chosenDices.getChildren().clear();
        bottemPane.getChildren().clear();
        List<Integer> rolls = roll.getRolls();
        int diceCounter = 0;
        for (Integer rolled : rolls) {
            dices[diceCounter].setFace(rolled);
            thrownDices.getChildren().add(dices[diceCounter]);
            diceCounter++;
        }
        Map<Integer, Integer> chosen = roll.getSelected();
        for (Map.Entry<Integer, Integer> choice : chosen.entrySet()) {
            for (int i = 0; i < choice.getValue(); i++) {
                dices[diceCounter].setFace(choice.getKey());
                chosenDices.getChildren().add(dices[diceCounter]);
                diceCounter++;
            }
        }
        bottemPane.setRight(thrownDices);
        bottemPane.setLeft(chosenDices);

    }

    /**
     * Vernieuwd de tegels op het spelbord
     * @param tegels
     */
    public void updateTegels(List<Tegel> tegels) {
        topPane.getChildren().clear();
        this.tegels = new ArrayList<>();
        for (int i = 0; i < tegels.size(); i++) {
            Tegel tegel = tegels.get(i);
            this.tegels.add(new TegelImage(String.format("resources/Tegel%d.png", tegel.getNumber()), tegel.getNumber(), tegel.getWurms()));
            this.tegels.get(i).setFitHeight(260);
            this.tegels.get(i).setFitWidth(130);
            if (i < 8) {
                topPane.add(this.tegels.get(i), i, 1);
            } else {
                topPane.add(this.tegels.get(i), i - 8, 2);
            }
        }
    }

    /**
     * Vernieuwd de afbeelding van de speler wanneer deze een tegel heeft genomen/verloren uit het spel
     * @param players spelers
     * @param currentRoll huidige worp
     */
    public void updatePlayers(List<Player> players, Roll currentRoll) {
        currentPlayer.setText(String.format("%s is aan de beurt!!", currentRoll.getPlayer().getName()));
        topPane.add(currentPlayer, 0, 0);
        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getTegels().size() > 0) {
                List<Tegel> playerTegels = players.get(i).getTegels();
                Tegel tegel = playerTegels.get(playerTegels.size() - 1);
                TegelImage player = this.players.get(i);
                player.setImage(new Image(String.format("resources/Tegel%d.png", tegel.getNumber())));
                player.setNumber(tegel.getNumber());
                player.setWurms(tegel.getWurms());
            } else {
                TegelImage player = this.players.get(i);
                player.setImage(new Image("resources/Player1.png"));
                player.setNumber(0);
                player.setWurms(0);
            }
        }
    }

    /**
     * Geeft een alert weer wanneer je kapot bent gespeeld
     */
    public void kapot() {
        Alert kapot = new Alert(Alert.AlertType.INFORMATION);
        kapot.setTitle("Kapot");
        kapot.setHeaderText("Je bent kapot gespeeld!!");
        kapot.showAndWait();
    }

    public List<TegelImage> getPlayers() {
        return players;
    }

    public List<TegelImage> getTegels() {
        return tegels;
    }

    public HBox getThrownDices() {
        return thrownDices;
    }

    public HBox getChosenDices() {
        return chosenDices;
    }

    public DiceImage[] getDices() {
        return dices;
    }

    public List<Label> getNames() {
        return names;
    }

    public GridPane getCenterPane() {
        return topPane;
    }

    public Button getThrowDice() {
        return throwDice;
    }

    public Label getCurrentPlayer() {
        return currentPlayer;
    }

    public Button getStopRoll() {
        return stopRoll;
    }

    /**
     * Geeft een alert weer van de speler die gewonnen is na afloop van het spel
     * @param winner Winnaar
     */
    public void endGame(Player winner) {
        Alert end = new Alert(Alert.AlertType.INFORMATION);
        end.setTitle("Game ended");
        end.setHeaderText(String.format("""
                The game has ended!!
                %s has won the game""", winner.getName()));
        end.showAndWait();
    }
}
