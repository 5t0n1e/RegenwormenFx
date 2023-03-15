package View.Player.Names;

import Model.Player;
import Model.RegenwormenException;
import Model.RegenwormenModel;
import View.Regenwormen.RegenwormenPresenter;
import View.Regenwormen.RegenwormenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class NamesPresenter {
    private RegenwormenModel model;
    private NamesView view;

    public NamesPresenter(RegenwormenModel model, NamesView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }
    private void handleEvents() {
        view.getConfirm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    List<Player> players = new ArrayList<>();
                    for (TextField playerName : view.getPlayerNames()) {
                        if (playerName.getText().isEmpty()) {
                            throw new RegenwormenException("Gelieve alle namen in te vullen!!");
                        }
                        players.add(new Player(playerName.getText()));
                        model.setPlayers(players);
                    }
                    RegenwormenView namesView = new RegenwormenView();
                    RegenwormenPresenter presentor = new RegenwormenPresenter(model, namesView);
                    Scene scene = view.getScene();
                    scene.setRoot(namesView);
                } catch (RegenwormenException ex) {
                    alert(ex.getMessage());
                }
            }
        });
    }
    private void alert(String message) {
        Alert ex = new Alert(Alert.AlertType.ERROR, message);
        ex.showAndWait();
    }

}
