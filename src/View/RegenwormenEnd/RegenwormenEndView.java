package View.RegenwormenEnd;

import Model.Player;
import Model.Tegel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class RegenwormenEndView extends BorderPane {
    Button restart;
    Label title;
    Label[] playerNames, info;
    ImageView[] tegels;

    /**
     * Initialiseert de gegevens van de gewonnen speler en de bijhorende score
     * @param players Spelers
     */
    public void initPlayers(List<Player> players) {
        title = new Label(String.format("The game has ended, %s has won!!", players.get(0).getName()));
        setTop(title);
        title.setAlignment(Pos.CENTER);

        restart = new Button("Restart");
        setBottom(restart);
        restart.setAlignment(Pos.CENTER);

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
                tegels.getChildren().add(new ImageView("resources/TegelTemplate.png"));
            }
            centerPane.add(tegels, 2, i);
        }
        setCenter(centerPane);
    }
}
