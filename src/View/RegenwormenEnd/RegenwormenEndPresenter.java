package View.RegenwormenEnd;

import Model.FileHandeling;
import Model.Player;
import Model.RegenwormenModel;
import View.Home.HomePresenter;
import View.Home.HomeView;
import View.Regenwormen.RegenwormenView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.util.List;

public class RegenwormenEndPresenter {
    private RegenwormenEndView view;
    private RegenwormenModel model;
    private FileHandeling fileHandeling;
    public RegenwormenEndPresenter(RegenwormenModel model, RegenwormenEndView view, FileHandeling fileHandeling) {
        this.view = view;
        this.model = model;
        this.fileHandeling = fileHandeling;
        List<Player> players = model.getPlayers();
        fileHandeling.updateHighScores(players.get(0));
        view.initPlayers(players);
        handleEvents();
    }

    public void handleEvents() {
        view.getRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HomeView homeView = new HomeView();
                List<Integer>options = fileHandeling.getOptions();
                model = new RegenwormenModel(options.get(0), options.get(1));
                HomePresenter presenter = new HomePresenter(model, homeView, fileHandeling);
                Scene scene = view.getScene();
                scene.setRoot(homeView);
            }
        });
    }
}
