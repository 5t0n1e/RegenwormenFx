package View.Player.Names;

import Model.Player;
import Model.RegenwormenModel;
import View.Regenwormen.RegenwormenPresentor;
import View.Regenwormen.RegenwormenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class NamesPresentor {
    private RegenwormenModel model;
    private NamesView view;

    public NamesPresentor(RegenwormenModel model, NamesView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }
    private void handleEvents() {
        view.getConfirm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Player> players = new ArrayList<>();
                for (TextField playerName : view.getPlayerNames()) {
                    if (playerName.getText().isEmpty()) {
                        //Throw exception
                    }
                    players.add(new Player(playerName.getText()));
                    model.setPlayers(players);
                }
                RegenwormenView namesView = new RegenwormenView();
                RegenwormenPresentor presentor = new RegenwormenPresentor(model, namesView);
                Scene scene = view.getScene();
                scene.setRoot(namesView);
            }
        });
    }

}
