package View.RegenwormenEnd;

import Model.Player;
import Model.Tegel;
import View.TegelImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class RegenwormenEndView extends BorderPane {
    Button restart;
    Label title;
    Label[] playerNames, info;
    public RegenwormenEndView() {
        setBackground(new Background(new BackgroundImage(new Image("resources/home.png"), null, null, null, null)));
    }

    public void initPlayers(List<Player> players) {
        title = new Label(String.format("The game has ended, %s has won!!", players.get(0).getName()));
        title.setAlignment(Pos.CENTER);
        setTop(title);

        restart = new Button("Restart");
        restart.setAlignment(Pos.BOTTOM_CENTER);
        setBottom(restart);

        playerNames = new Label[players.size()];
        info = new Label[players.size()];
        GridPane centerPane = new GridPane();
        for (int i = 0; i < players.size(); i++) {
            playerNames[i] = new Label(players.get(i).getName());
            info[i] = new Label(String.format("Totale wormen: %d", players.get(i).getTotalWurms()));
            centerPane.add(playerNames[i], 0, i);
            centerPane.add(info[i], 1, i);
            HBox tegels = new HBox();
            for (Tegel tegel : players.get(i).getTegels()) {
                TegelImage tegelImage = new TegelImage(String.format("resources/Tegel%d.png", tegel.getNumber()));
                tegelImage.setFitWidth(150);
                tegelImage.setFitHeight(300);
                tegels.getChildren().add(tegelImage);
            }
            centerPane.add(tegels, 2, i);
        }
        setCenter(centerPane);
    }

    public Button getRestart() {
        return restart;
    }
}