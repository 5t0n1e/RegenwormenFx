package View.Regenwormen;

import Model.Player;
import Model.Roll;
import Model.Tegel;
import View.DiceImage;
import View.TegelImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.*;

public class RegenwormenView extends BorderPane {
    private List<TegelImage> tegels, players;
    private DiceImage[] dices;
    private List<Label> names;
    private GridPane topPane;
    private BorderPane playboard;
    private Button throwDice, stopRoll;
    private Label currentPlayer;
    private HBox thrownDices, chosenDices;

    public RegenwormenView() {
        setBackground(new Background(new BackgroundImage(new Image("resources/home.png"), null, null, null, null)));

        topPane = new GridPane();
        topPane.setAlignment(Pos.CENTER);
        setCenter(topPane);

        playboard = new BorderPane();
        playboard.setBackground(new Background(new BackgroundImage(new Image("resources/spelbord.png"), null, null, null, null)));
        playboard.setMinHeight(273);
        playboard.setMinWidth(820);
        playboard.setMaxHeight(273);
        playboard.setMaxWidth(820);
        playboard.setPadding(new Insets(15));

        throwDice = new Button();
        throwDice.setBackground(new Background(new BackgroundImage(new Image("resources/throw.png"), null, null, null, null)));
        throwDice.setMinSize(259, 194);
        throwDice.getStyleClass().remove(0);

        stopRoll = new Button();
        stopRoll.setBackground(new Background(new BackgroundImage(new Image("resources/stop.png"), null, null, null, null)));
        stopRoll.setMinSize(259, 194);
        stopRoll.getStyleClass().remove(0);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        HBox bottomPanes = new HBox(throwDice, region1, playboard, region2, stopRoll);
        bottomPanes.setPadding(new Insets(50));
        bottomPanes.setSpacing(50);
        setBottom(bottomPanes);

        dices = new DiceImage[8];

        thrownDices = new HBox();
        thrownDices.setSpacing(10);
        thrownDices.setAlignment(Pos.TOP_RIGHT);

        chosenDices = new HBox();
        chosenDices.setSpacing(10);
        chosenDices.setAlignment(Pos.BOTTOM_LEFT);

        for (int i = 0; i < dices.length; i++) {
            dices[i] = new DiceImage();
        }
    }

    public void initialiseNodes(List<Player> players, List<Tegel> tegels) {
        updateTegels(tegels);
        showPlayers(players);
        currentPlayer = new Label(String.format("%s is aan de beurt!!", players.get(0).getName()));
        topPane.add(currentPlayer, 0, 0, 10, 1);
        topPane.setVgap(20);
        topPane.setHgap(20);
    }

    private void showPlayers(List<Player> players) {
        this.players = new ArrayList<>();
        this.names = new ArrayList<>();
        Random gen = new Random();
        VBox playersRight = new VBox();
        VBox playersLeft = new VBox();
        int split = players.size() / 2;
        for (int i = 0; i < players.size(); i++) {
            this.players.add(new TegelImage(String.format("resources/Player%d.png", gen.nextInt(1, 5))));
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

    public void updateDices(Roll roll) {
        thrownDices.getChildren().clear();
        chosenDices.getChildren().clear();
        playboard.getChildren().clear();
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
        playboard.setRight(thrownDices);
        playboard.setLeft(chosenDices);
    }

    public void updateTegels(List<Tegel> tegels) {
        topPane.getChildren().clear();
        this.tegels = new ArrayList<>();
        for (int i = 0; i < tegels.size(); i++) {
            Tegel tegel = tegels.get(i);
            this.tegels.add(new TegelImage(String.format("resources/Tegel%d.png", tegel.getNumber()), tegel.getNumber()));
            this.tegels.get(i).setFitHeight(260);
            this.tegels.get(i).setFitWidth(130);
            if (i < 8) {
                topPane.add(this.tegels.get(i), i, 1);
            } else {
                topPane.add(this.tegels.get(i), i - 8, 2);
            }
        }
    }

    public void updatePlayers(List<Player> players, Roll currentRoll) {
        currentPlayer.setText(String.format("%s is aan de beurt!!", currentRoll.getPlayer().getName()));
        topPane.add(currentPlayer, 0, 0, 10, 1);

        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getTegels().size() > 0) {
                List<Tegel> playerTegels = players.get(i).getTegels();
                Tegel tegel = playerTegels.get(playerTegels.size() - 1);
                TegelImage player = this.players.get(i);
                player.setImage(new Image(String.format("resources/Tegel%d.png", tegel.getNumber())));
                player.setNumber(tegel.getNumber());
            } else {
                TegelImage player = this.players.get(i);
                player.setImage(new Image(player.getInitPath()));
                player.setNumber(0);
            }
        }
    }

    public void kapot() {
        Alert kapot = new Alert(Alert.AlertType.INFORMATION);
        kapot.setTitle("Kapot");
        kapot.setHeaderText("Je bent kapot gespeeld!!");
        kapot.showAndWait();
    }

    public List<TegelImage> getPlayers() {
        return players;
    }

    public HBox getThrownDices() {
        return thrownDices;
    }

    public DiceImage[] getDices() {
        return dices;
    }

    public Button getThrowDice() {
        return throwDice;
    }

    public Button getStopRoll() {
        return stopRoll;
    }
}
