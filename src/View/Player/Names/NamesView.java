package View.Player.Names;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        }
        menu.getChildren().add(confirm);
        menu.setAlignment(Pos.CENTER);
        setCenter(menu);
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
