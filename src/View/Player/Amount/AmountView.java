package View.Player.Amount;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AmountView extends BorderPane {
    private TextField playerAmountInput;
    private Label info;
    private Button confirm;

    public AmountView() {
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        VBox menu = new VBox(info, playerAmountInput, confirm);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(20);
        setCenter(menu);
        setBackground(new Background(new BackgroundImage(new Image("resources/home.png"), null, null, null, null)));
    }

    private void initialiseNodes() {
        playerAmountInput = new TextField();
        info = new Label("How many players?");
        confirm = new Button("CONFIRM");
    }

    public TextField getPlayerAmountInput() {
        return playerAmountInput;
    }

    public Button getConfirm() {
        return confirm;
    }
}
