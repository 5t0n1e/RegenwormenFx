package View.Regenwormen;

import Model.Player;
import Model.Roll;
import Model.Tegel;
import View.Die;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class RegenwormenView extends BorderPane {
    private List<ImageView> players, tegels;
    private Die[] dices;
    private List<Label> names;
    private GridPane topPane, bottemPane;
    private Button throwDice, stopRoll;
    private Label currentPlayer;
    private HBox thrownDices, chosenDices;
    private VBox centerPanes;
    public RegenwormenView() {
        topPane = new GridPane();
        bottemPane = new GridPane();
        centerPanes = new VBox(topPane, bottemPane);
        setCenter(centerPanes);
        throwDice = new Button("Gooi dices");
        stopRoll = new Button("Stop worp");
        dices = new Die[8];
        thrownDices = new HBox();
        chosenDices = new HBox();
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Die();
        }
    }
    public void initialiseNodes(List<Player> players, List<Tegel> tegels) {
        updateTegels(tegels);
        showPlayers(players);        
        setBottom(new HBox(throwDice, stopRoll));
        currentPlayer = new Label(String.format("%s is aan de beurt!!", players.get(0).getName()));
        topPane.add(currentPlayer, 0, 0);
        topPane.setVgap(20);
        topPane.setHgap(20);
    }
    private void showPlayers(List<Player> players) {
        this.players = new ArrayList<>();
        this.names = new ArrayList<>();
        VBox playersRight = new VBox();
        VBox playersLeft = new VBox();
        int split = players.size()/2;
        for (int i = 0; i < players.size(); i++) {
            this.players.add(new ImageView("resources/player.png"));
            this.players.get(i).setFitWidth(200);
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
        bottemPane.add(thrownDices, 4, 4);
        bottemPane.add(chosenDices, 1, 5);
        // setBottom(bottemPane);
    }
    public void updateTegels(List<Tegel> tegels) {
        topPane.getChildren().clear();
        this.tegels = new ArrayList<>();
        for (int i = 0; i < tegels.size(); i++) {
            this.tegels.add(new ImageView("resources/TegelTemplate.png"));
            this.tegels.get(i).setFitHeight(260);
            this.tegels.get(i).setFitWidth(130);
            if (i<8) {
                topPane.add(this.tegels.get(i), i, 1);
            } else {
                topPane.add(this.tegels.get(i), i-8, 2);
            }
        }
    }

    public void updatePlayers(List<Player> players, Roll currentRoll) {
        currentPlayer.setText(String.format("%s is aan de beurt!!", currentRoll.getPlayer().getName()));
        topPane.add(currentPlayer, 0, 0);
        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getTegels().size() > 0)
                this.players.get(i).setImage(new Image("resources/TegelTemplate.png")); // players.get(i).getTegels().get(i)
        }
    }
    public void kapot() {
        Alert kapot = new Alert(Alert.AlertType.INFORMATION);
        kapot.setTitle("Kapot");
        kapot.setHeaderText("Je bent kapot gespeeld!!");
        kapot.showAndWait();
    }
    public List<ImageView> getPlayers() {
        return players;
    }
    public List<ImageView> getTegels() {
        return tegels;
    }
    public HBox getThrownDices() {
        return thrownDices;
    }
    public HBox getChosenDices() {
        return chosenDices;
    }
    public Die[] getDices() {
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
}
