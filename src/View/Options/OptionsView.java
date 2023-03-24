package View.Options;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
        setBackground(new Background(new BackgroundImage(new Image("resources/home.png"), null, null, null, null)));
        openConfig = new Button("KIES CONFIG");
        openConfig.setMinSize(160, 40);
        openConfig.setBackground(new Background(new BackgroundFill(Paint.valueOf("946845FF"), new CornerRadii(10), null)));
        openConfig.setFont(new Font("Nimbus Mono PS", 20));
        openHighScores = new Button("KIES HIGHSCORE");
        openHighScores.setMinSize(160, 40);
        toepassen = new Button("Toepassen");
        toepassen.setMinSize(100, 40);
        back = new Button("Terug");
        back.setMinSize(100, 40);
        tegel = new Label("Tegel hoeveelheid:");
        tegelAmount = new TextField();
        dice = new Label("Dobbelstenen hoeveelheid:");
        diceAmount = new TextField();
    }

    private void layoutNodes() {
        VBox buttons = new VBox(openHighScores, openConfig, tegel, tegelAmount, dice, diceAmount, toepassen, back);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        setCenter(buttons);
    }

    public Button getOpenConfig() {
        return openConfig;
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
