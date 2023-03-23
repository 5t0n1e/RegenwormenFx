package View.options;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;


public class OptionsView extends BorderPane {
    Button openConfig, openHighScores, toepassen, back;
    Label tegel, dice;
    TextField tegelAmount, diceAmount;

    public OptionsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        openConfig = new Button("Choose config file");
        openHighScores = new Button("Choose highscores file");
        toepassen = new Button("Toepassen");
        back = new Button("Terug");
        tegel = new Label("Tegel hoeveelheid:");
        tegelAmount = new TextField();
        dice = new Label("Dobbelstenen hoeveelheid:");
        diceAmount = new TextField();

    }

    private void layoutNodes() {
        VBox buttons = new VBox(openHighScores, openConfig, tegel, tegelAmount, dice, diceAmount, toepassen, back);
        buttons.setAlignment(Pos.CENTER);
        setCenter(buttons);
    }

    public Button getOpenConfig() {
        return openConfig;
    }

    public Button getOpenHighScores() {
        return openHighScores;
    }

    public Button getToepassen() {
        return toepassen;
    }

    public TextField getTegelAmount() {
        return tegelAmount;
    }

    public TextField getDiceAmount() {
        return diceAmount;
    }

    public Button getBack() {
        return back;
    }

    public void updateTextFields(List<Integer> options) {
        tegelAmount.setText(options.get(0).toString());
        diceAmount.setText(options.get(1).toString());
    }
}
