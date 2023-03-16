package View.Player.Names;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NamesView extends BorderPane {
    private TextField[] playerNames;
    private Label info;
    private Button confirm;
    public NamesView(int nameAmount) {
        initialiseNodes(nameAmount);
        layoutNodes();
    }
    private void layoutNodes() {
        VBox menu = new VBox(info);
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new TextField();
            menu.getChildren().add(playerNames[i]);
            playerNames[i].setMinSize(100, 20);
            playerNames[i].setMaxSize(100, 20);
        }
        menu.getChildren().add(confirm);
        menu.setAlignment(Pos.CENTER);
        setCenter(menu);
        setBackground(new Background(new BackgroundImage(new Image("resources/Home_Achtergrond.jpg"), null, null, null, null)));
    }

    private void initialiseNodes(int nameAmount) {
        playerNames = new TextField[nameAmount];
        info = new Label("Names?");
        confirm = new Button("CONFIRM");
    }

    public TextField[] getPlayerNames() {
        return playerNames;
    }

    public Button getConfirm() {
        return confirm;
    }
}
